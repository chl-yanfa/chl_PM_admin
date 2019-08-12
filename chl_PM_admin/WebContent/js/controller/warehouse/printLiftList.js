/*打印提货单*/
$(document).ready(function(){
	//初始化表格数据
	initDateGrid();
	reLoadData(0);
});
//初始化数据表格
function initDateGrid(){
	var toolbarType = [
		{id:'searchText'},'&nbsp;',
		{text : '查询',id : 'iconSearch'},'&nbsp;',
		{text : '重置',id : 'iconReset'}
	];
	$("#printLiftList").datagrid({
    	loadMsg : "数据加载中,请稍后……",
        nowrap : true,
        striped : true,
        fit : false,
        fitColumns : true,
        url : URLMODULE.getTakeCarList,
        method : 'post',
        autoLoad:false,
        idField : 'id',
        view:tcwOrderInfoView,
        rowTitleFormatter:function(row){
        	return "拍卖时间："+formatDatebox(row['paimaiDate'])+"<span style='margin-left:30px'/>拍品编码："+row['auctionNo']+"<span style='margin-left:30px'/>";
        },
        columns : [[
            {title : '拍品基本信息', field : 'carCode', width : 280, align : 'left',halign:'center',formatter: function(value,rec,index){
            	return carHeadInfo(rec);
            }},
            {title:'是否打印',field:'isPrint',width:40,align:'center'},
            {title:'拍品状态',field:'vehicleState',width:40,align:'center'},
            {title : '操作', field : 'id', width : 40, align : 'center',formatter:function(value,row,index){
            	var auctionId=row.auctionId;
            	var openPrint="openPrint('"+auctionId+"')";
            	var styleStr='href="javascript:void(0);" style="font-size: 14px;color: #4EA2FF;"';
            	var optionStr = '<a '+styleStr+' onclick="'+openPrint+'">打印</a>';
                return optionStr;
           }}
        ]],
        toolbar : toolbarType,
        pagination : true,
        singleSelect : true,
        fitStyle : true
    });
	   
	$('#searchText').searchbox({
	   width :222,
	   prompt : '品牌型号/车牌号',
	   searcher:function(value,name){
		   $('#searchText').searchbox("setValue",value.toUpperCase());
		   reLoadData(0);
	   }
	});
	$('#iconSearch').click(function() {
		reLoadData(0);
	});
	$('#iconReset').click(function() {
		$('#searchText').searchbox('clear');
		reLoadData(0);
	});
	
}

//重新加载表格数据
function reLoadData(page){
	var loadType = page==1?"reload":"load";
	$("#printLiftList").datagrid(loadType, {
		keywords: $('#searchText').searchbox('getValue').trim()
    });
}
//打印单
function openPrint(auctionId) {
	$.ajax({type: 'post', url:'../../warehouse/getTakeCarBill', dataType: "json",
		data:{auctionId : auctionId},
		success:function(data){
			$('#liftList-DIALOG').dialog({
				title: "查看提货单",
				width: 1100,
				resizable: true,
				modal: true,
				href: "../../html/warehouse/takeCarBill.html",
				onLoad: function () {
					var auction=data.entity;
					if(auction['transDrivingVin']=='车架号变形'){
						auction.drivingVin = '车架号变形';
					}
					if(auction['hasLicense'] =="有牌"){
						auction.licenseNumber = auction.licenseNumber;
					}else if(auction['hasLicense']!=null){
						auction.licenseNumber = auction['hasLicense'];
					}else{
						auction.licenseNumber="车牌未提供"
					}
					$("#lift-all").find("span[col]").each(function(){
						$(this).html(data.entity[$(this).attr("col")]);
					});
				},
				onClose:function(){
					reLoadData(1);
				}
			});
		},
	});
}