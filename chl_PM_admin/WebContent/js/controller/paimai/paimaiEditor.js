$(function() {
	initPaimai();
	/*选择全部/全不选*/
    $("#checkAll").click(function(){
    	if ($(this).prop("checked")==true){
            $("input[name='auctionCarBox']").prop("checked", true);
        }else{
            $("input[name='auctionCarBox']").prop("checked", false);
        }
    });
});
function initPaimai(){
	var paimaiId = getParamValue("id");
	$.post('../../paimai/getPaimaiById',{
		id : paimaiId
	},function(res) {
		var paimai=res.entity;
		$(".paimai-top").find("span[col]").each(function(){
			$(this).html(paimai[$(this).attr("col")]);
		});
		$(".paimai-middle").find("td[col]").each(function(){
			$(this).html(paimai[$(this).attr("col")]);
		});
		var auctionCount=paimai.auctionCount;
		var dealCount=paimai.dealCount;
		var abortiveCount=paimai.abortiveCount;
		if(auctionCount==0||(dealCount+abortiveCount==auctionCount)){
			$('.button-div').html('');
		}else{
			$('.button-div').html('<button class="btn" onclick="publish()">发布拍卖会</button>');
		}
		var pmhId=paimai.pmhId;
		$("#auctionCar").datagrid({
			loadMsg : "数据加载中,请稍后……",
			nowrap:true,
	        striped : true,
	        fitColumns : true,
	        url : '../../paimai/getAuctionCarList',
	        method : 'post',
	        queryParams: {
	        	pmhId:pmhId
	    	},
	        autoLoad:false,
	        singleSelect : true,
	        view:tcwOrderInfoView,
	        rowTitleCheckboxFormatter:function(row){
	        	return "拍品编码："+row['auctionNo'];
	        },
	        columns : [[
	        	{title : '拍品基本信息', field : 'carCode', width : 260, align : 'left',halign:'center',formatter: function(value,rec,index){
	        		return carHeadInfo(rec);
	            }},
	            {title : '拍卖序号', field : 'pmOrder', width : 40, align : 'center',
					editor:{
						type:'numberbox',options:{required:true}
				}},
	            {title : '商品状态',field:'orderStateS',width:60,align:'center'},
	            {title : '起拍价', field : 'startingPrice', width : 60,align:'center'},
	            {title : '保留价',field:'reservePrice',width:60,align:'center'},
	            {title : '参拍保证金',field:'auctionCashDeposit',width:60,align:'center'},
	            {title : '操作', field : 'auctionId', width : 140, align : 'center',formatter:function(value,row,index){
	            	var auctionId=value;
	            	var getDetail="getDetail('"+auctionId+"')";
	            	var editor="editor('"+auctionId+"')";
	            	var auctionSet="auctionSet('"+auctionId+"')";
	            	var deleteThis="deleteThis('"+auctionId+"')";
	            	var saverow="saverow(this)";
	            	var styleStr='href="javascript:void(0);" style="font-size: 14px;color: #4EA2FF;"';
	            	var splitStr='&nbsp;<font style="color: #4EA2FF;">|</font>&nbsp;';
	            	var optionStr = '';
	            	optionStr += '<a '+styleStr+' onclick="'+getDetail+'">查看详情</a>'+splitStr;
	            	optionStr += '<a '+styleStr+' onclick="'+editor+'">编辑信息</a><br/>';
	            	optionStr += '<a '+styleStr+' onclick="'+deleteThis+'">删除拍品</a><br/>';
	            	optionStr += '<a '+styleStr+' onclick="'+auctionSet+'">查看参拍设置</a><br/>';
	            	if (row.editing){
						optionStr += '<a '+styleStr+' onclick="'+saverow+'">保存拍卖序号</a>';
					} else {
						optionStr += '<a '+styleStr+' onclick="editrow(this)">修改拍卖序号</a>';
					}

	                return optionStr;
	            }}
	        ]],
	        onLoadSuccess: function(index,field,value){
	        	$("input[name='auctionCarBox']").click(function(){
	            	if ($(this).prop("checked")==false){
	            		$("#checkAll").prop("checked", false);
	            	}
	            });
	        },
			onBeforeEdit:function(index,row){
				row.editing = true;
				updateRow(index);
			},
			onAfterEdit:function(index,row){
				row.editing = false;
				updateRow(index);
				$.post('../../paimai/editPmOrder',{
					auctionId:row.auctionId,
					pmOrder:row.pmOrder
				},function(data) {
					/*$.messager.alert('提示', "更新成功",'info');*/
				});
			},
			onCancelEdit:function(index,row){
				row.editing = false;
				updateRow(index);
			}
	    });
		$("#auctionCar").datagrid("load");
	});
}
function updateRow(index){
	$('#auctionCar').datagrid('updateRow',{
		index: index,
		row:{}
	});
}
function getRowIndex(target){
	var tr = $(target).closest('tr.datagrid-row');
	return parseInt(tr.attr('datagrid-row-index'));
}
function editrow(target){
	$('#auctionCar').datagrid('beginEdit', getRowIndex(target));
}
function saverow(target){
	$('#auctionCar').datagrid('endEdit', getRowIndex(target));
}
function cancelrow(target){
	$('#auctionCar').datagrid('cancelEdit', getRowIndex(target));
}
function batchDeleteAuctionCar(){
	var length=$("input[name='auctionCarBox']:checked").length;
	if(length>0){
		$.messager.confirm('提示', '确定删除吗?', function(r){
			if (r){
				var arr = new Array();
				$("input[name='auctionCarBox']:checked").each(function(i){
			        arr[i] = $(this).val();
			    });
			    var vals = arr.join(",");
				$.post('../../paimai/batchDeleteAuctionCar',{
					selAuctionCarStr:vals
				},function(data) {
					$.messager.alert('提示', "删除成功",'info',function(){
						var paimaiId = getParamValue("id");
						window.location.href="../../html/paimai/paimaiEditor.html?id="+paimaiId;						
					});
				});
			}
		});
	}else{
		$.messager.alert('系统提示', '请勾选要删除的参拍车辆!');
		return;
	}
	
}
function publish(){
	var paimaiId = getParamValue("id");
	$.post('../../paimai/publishPaimai',{
		paimaiId : paimaiId
	},function(res) {
		if(res.returnCode=='000000'){
			$.messager.alert('提示', "发布成功",'info',function(){
				var paimaiId = getParamValue("id");
				window.location.href="../../html/paimai/paimaiPublish.html?id="+paimaiId;						
			});
		}else{
			$.messager.alert('提示', "发布失败");
		}
	});
}
function paimaiStatistics(){
	var paimaiId = getParamValue("id");
	window.location.href="../../html/paimai/paimaiStatistics.html?id="+paimaiId;
}
function addAuctionCar(){
	var paimaiId = getParamValue("id");
	window.location.href="../../html/paimai/selectAuctionList.html?id="+paimaiId;
}
/*查看*/
function getDetail(auctionId){
	window.location.href="../../html/auction/auctionDetails.html?id="+auctionId;
}
/*编辑*/
function editor(auctionId){
	window.location.href="../../html/auction/editorAuction.html?id="+auctionId;
}
/*删除*/
function deleteThis(auctionId){
	$.messager.confirm('提示', '确定删除吗?', function(r){
		if (r){
			$.post('../../paimai/deleteAuctionCar',{
				auctionId:auctionId
			},function(data) {
				$.messager.alert('提示', "删除成功",'info',function(){
					var paimaiId = getParamValue("id");
					window.location.href="../../html/paimai/paimaiEditor.html?id="+paimaiId;						
				});
			});
		}
	});
}
/*参拍设置*/
function auctionSet(auctionId){
	window.location.href="../../html/auction/auctionSet.html?id="+auctionId;
}
