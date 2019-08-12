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
	            {title : '操作', field : 'auctionId', width : 60, align : 'center',formatter:function(value,row,index){
	            	var auctionId=value;
	            	var auctionSetId=row.auctionSetId;
	            	var enterResult="enterResult('"+auctionId+"','"+auctionSetId+"')";/*查看拍卖结果*/
	            	var styleStr='href="javascript:void(0);" style="font-size: 14px;color: #4EA2FF;"';
	            	var splitStr='&nbsp;<font style="color: #4EA2FF;">|</font>&nbsp;';
	            	var optionStr = '';
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
	        }
	    });
		$("#auctionCar").datagrid("load");
	});
}
function paimaiStatistics(){
	var paimaiId = getParamValue("id");
	window.location.href="../../html/paimai/paimaiStatistics.html?id="+paimaiId;
}
/*查看*/
function getDetail(auctionId){
	window.location.href="../../html/auction/auctionDetails.html?id="+auctionId;
}
/*录入拍卖结果*/
function enterResult(auctionId,auctionSetId){
	window.location.href="../../html/paimai/enterResult.html?auctionId="+auctionId+"&auctionSetId="+auctionSetId;
}
