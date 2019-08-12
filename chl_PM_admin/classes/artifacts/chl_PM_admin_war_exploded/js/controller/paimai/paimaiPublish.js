$(function() {
	initPaimai();
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
		$('.button-div').html('');
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
	        rowTitleFormatter:function(row){
	        	return "拍品编码："+row['auctionNo'];
	        },
	        columns : [[
	        	{title : '拍品基本信息', field : 'carCode', width : 260, align : 'left',halign:'center',formatter: function(value,rec,index){
	        		return carHeadInfo(rec);
	            }},
	            {title : '序号', field : 'pmOrder', width : 30, align : 'center',
					editor:{
						type:'numberbox',options:{required:true}
				}},
				{title : '起拍价', field : 'startingPrice', width : 40,align:'center'},
				{title : '保留价', field : 'reservePrice', width: 40,align:'center'},
				{title : '当前价', field : 'highestPrice', width : 40,align:'center'},
	            {title : '商品状态',field:'orderStateS',width:40,align:'center'},
	            {title : '买受人',field:'dealMember',width:50,align:'center'},
	            {title : '电话号码',field:'phone',width:50,align:'center'},
	            {title : '佣金', field : 'commission', width : 40,align:'center'},
	            {title : '其他费用',field:'otherPrice',width:40,align:'center'},
	            {title : '总计',field:'totalPrice',width:40,align:'center'},
	            {title : '操作', field : 'auctionId', width : 100, align : 'center',formatter:function(value,row,index){
	            	var auctionId=value;
	            	var auctionSetId=row.auctionSetId;
	            	var getDetail="getDetail('"+auctionId+"')";
	            	var editor="editor('"+auctionId+"')";
	            	var auctionSet="auctionSet('"+auctionId+"')";
	            	var returnAuction="returnAuction('"+auctionId+"')";/*撤拍*/
	            	var cutAuction="cutAuction('"+auctionId+"','"+auctionSetId+"')";/*切拍*/
	            	var abortiveAuction="abortiveAuction('"+auctionId+"','"+auctionSetId+"')";/*流拍*/
	            	var auctionAgain="auctionAgain('"+auctionSetId+"')";/*本场复拍*/
	            	var enterResult="enterResult('"+auctionId+"','"+auctionSetId+"')";/*查看拍卖结果*/
	            	var saverow="saverow(this)";
	            	var styleStr='href="javascript:void(0);" style="font-size: 14px;color: #4EA2FF;"';
	            	var splitStr='&nbsp;<font style="color: #4EA2FF;">|</font>&nbsp;';
	            	var optionStr = '';
	            	optionStr += '<a '+styleStr+' onclick="'+editor+'">编辑信息</a><br/>';
	            	optionStr += '<a '+styleStr+' onclick="'+auctionSet+'">查看参拍设置</a><br/>';
	            	if(row.orderState=='10'||row.orderState=='20'){
	            		optionStr += '<a '+styleStr+' onclick="'+returnAuction+'">撤拍</a><br/>';
	            	}else if(row.orderState=='30'){
	            		optionStr += '<a '+styleStr+' onclick="'+cutAuction+'">切拍</a>'+splitStr;	            		
	            		optionStr += '<a '+styleStr+' onclick="'+abortiveAuction+'">流拍</a>'+splitStr;
	            		optionStr += '<a '+styleStr+' onclick="'+auctionAgain+'">本场复拍</a><br/>';
	            	}
	            	if (row.editing&&row.orderState=='10'){
						optionStr += '<a '+styleStr+' onclick="'+saverow+'">保存拍卖序号</a><br/>';
					} else if(row.orderState=='10'){
						optionStr += '<a '+styleStr+' onclick="editrow(this)">修改拍卖序号</a><br/>';
					}
	            	optionStr += '<a '+styleStr+' onclick="'+enterResult+'">查看拍卖结果</a>';	            		
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
					$.messager.alert('提示', "更新成功",'info');
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
/*参拍设置*/
function auctionSet(auctionId){
	window.location.href="../../html/auction/auctionSet.html?id="+auctionId;
}
/*撤拍*/
function returnAuction(auctionId){
	$.messager.confirm('提示', '确定要撤拍吗?', function(r){
		if (r){
			$.post('../../paimai/returnAuction',{
				auctionId:auctionId
			},function(data) {
				$.messager.alert('提示', "撤拍成功",'info',function(){
					var paimaiId = getParamValue("id");
					window.location.href="../../html/paimai/paimaiPublish.html?id="+paimaiId;						
				});
			});
		}
	});
}
/*切拍*/
function cutAuction(auctionId,auctionSetId){
	var paimaiId = getParamValue("id");
	window.location.href="../../html/paimai/cutAuction.html?auctionId="+auctionId+"&auctionSetId="+auctionSetId+"&paimaiId="+paimaiId;
}
/*流拍*/
function abortiveAuction(auctionId,auctionSetId){
	//流拍逻辑
	$.messager.confirm('提示', '确定要流拍吗?', function(r){
		if (r){
			$.post('../../auctionResult/abortiveAuction',{
				auctionId:auctionId,
				orderId:auctionSetId 
			},function(res) {
				if(res.returnCode=='000000'){
					$.messager.alert('提示', "流拍成功",'info',function(){
						var paimaiId = getParamValue("id");
						window.location.href="../../html/paimai/paimaiPublish.html?id="+paimaiId;						
					});					
				}else{
					$.messager.alert('提示', res.returnMsg,'info');		
				}
			});
		}
	});
}
/*本场复拍*/
function auctionAgain(auctionSetId){
	$.messager.confirm('提示', '确定要本场复拍吗?', function(r){
		if (r){
			$.post('../../paimai/againAuction',{
				auctionSetId:auctionSetId 
			},function(res) {
				if(res.returnCode=='000000'){
					$.messager.alert('提示', "操作成功",'info',function(){
						location.reload();
					});					
				}else{
					$.messager.alert('提示', res.returnMsg,'info');		
				}
			});
		}
	});
}
/*录入拍卖结果*/
function enterResult(auctionId,auctionSetId){
	window.location.href="../../html/paimai/enterResult.html?auctionId="+auctionId+"&auctionSetId="+auctionSetId;
}
