//车辆列表
$(document).ready(function(){
	//初始化表格数据
	initDateGrid();
	//初始化Tab页数据
	initTabs();
});
//初始化数据表格
function initDateGrid(){
	$("#list").datagrid({
		loadMsg : "数据加载中,请稍后……",
        nowrap : false,
        striped : true,
        fit : false,
        fitColumns : true,
        url : URLMODULE.getAuctionList,
        method : 'post',
        autoLoad:false,
        idField : 'id',
        view:tcwOrderInfoView,
        rowTitleFormatter:function(row){
        	return "拍品编码："+row['auctionNo'];
        },
        columns : [[
            {title : '拍品基本信息', field : 'carCode', width : 320, align : 'left',halign:'center',formatter: function(value,rec,index){
            	return carHeadInfo(rec);
            }},
            {title : '拍卖时间',field:'paimaiDate',width:100,align:'center'},
            {title : '业务登记', field : 'registState', width : 60,align:'center'},
            {title : '参拍审核',field:'subCompanyAudit',width:60,align:'center'},
            {title : '参拍设置',field:'setState',width:60,align:'center'},
            {title : '拍品状态',field:'vehicleState',width:80,align:'center'},
            {title : '操作', field : 'id', width : 100, align : 'center',formatter:function(value,row,index){
            	var carId=row.id;
            	var getDetails="getDetails('"+carId+"')";/*查看详情*/
            	var editor="editor('"+carId+"')";/*编辑*/
            	var auctionAudit="auctionAudit('"+carId+"')";/*拍品信息审核*/
            	var auctionSet="auctionSet('"+carId+"')";/*参拍设置*/
            	var auctionSetAudit="auctionSetAudit('"+carId+"')";/*参拍设置异常审核*/
            	var styleStr='href="javascript:void(0);" style="font-size: 14px;color: #4EA2FF;"';
            	var optionStr = '';
            	optionStr += '<a '+styleStr+' onclick="'+getDetails+'">查看</a>&nbsp;&nbsp;';
            	optionStr += '<a '+styleStr+' onclick="'+editor+'">编辑</a><br>';
            	optionStr += '<a '+styleStr+' onclick="'+auctionAudit+'">拍品信息审核</a><br>';
                if(row.subCompanyAuditFlag == 1 && row.setStateFlag == 0){
                	optionStr += '<a '+styleStr+' onclick="'+auctionSet+'">参拍设置</a><br>';                	
                }
                if(row.subCompanyAuditFlag == 1 && row.setStateFlag == 1){
                	optionStr += '<a '+styleStr+' onclick="'+auctionSet+'">查看参拍设置</a><br>';                	
                }
                return optionStr;
           }}
        ]],
        toolbar : toolbarType,
        pagination : true,
        singleSelect : true,
        fitStyle : true
    });
	//拍品类型
	$('#tb-auctionType').combobox({
    	editable : false,
    	width : 180,
		valueField:'id',
		textField:'value',
		data: [{id:"0",value: "全车配件"},{id:"1",value: "高价值配件"},{id:"2",value: "大宗配件"}],
		panelHeight:"auto"
	});
	//分公司审核状态
	$('#tb-auditState').combobox({
    	editable : false,
    	width : 180,
		valueField : 'id',
		textField : 'value',
		data: [{id:"0",value: "待审核"},{id: "1",value: "审核通过"},{id: "2",value: "已驳回"}],
		panelHeight:"auto"
	});
	//参拍设置
	$('#tb-auctionSet').combobox({
    	editable : false,
    	width : 180,
		valueField:'id',
		textField:'value',
		data: [{id:"0",value: "待设置"},{id:"1",value: "已设置"},{id:"2",value: "待审核"},{id:"3",value: "已驳回"}],
		panelHeight:"auto"
	});
	//拍卖时间
	$('#tb-paimaiDateStart').datetimebox({
		editable : false,
		width : 180,
		showSeconds : false
	});
	$('#tb-paimaiDateEnd').datetimebox({
		editable : false,
		width : 180,
		showSeconds : false
	});
	//业务登记
	$('#tb-registState').combobox({
    	editable : false,
    	width : 180,
		valueField:'id',
		textField:'value',
		data: [{id:"0",value: "未登记"},{id:"1",value: "已登记"},{id:"2",value: "已驳回"}],
		panelHeight:"auto"
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
		updateTab(0);
	});
	$('#iconReset').click(function() {
		$('#tb-auctionType').combobox('clear');
		$('#tb-auditState').combobox('clear');
		$('#tb-auctionSet').combobox('clear');
		$('#tb-paimaiDateStart').datetimebox('clear');
		$('#tb-paimaiDateEnd').datetimebox('clear');
		$('#tb-registState').combobox('clear');
		$('#searchText').searchbox('clear');
		updateTab(0);
	});
	$('#iconAdd').click(function() {
		window.location.href="../../html/auction/addAuction.html";
	});
}
//重新加载表格数据
function initReLoadData(type){
	var loadType = type==1?"reload":"load";
	$("#list").datagrid(loadType, {
		auctionType:$('#tb-auctionType').combobox('getValue'),
		subCompanyAuditId:$('#tb-auditState').combobox('getValue'),
		setStateId: $('#tb-auctionSet').combobox('getValue'),
		paimaiDateStart: $('#tb-paimaiDateStart').datetimebox("getValue"),
		paimaiDateEnd: $('#tb-paimaiDateEnd').datetimebox("getValue"),
		registStateId: $('#tb-registState').combobox('getValue'),
		keywords: $('#searchText').searchbox('getValue').trim(),
		auctionState: currentStatus
  });
}
function reLoadData(type){
	updateTab(type);
}

var currentStatus='';
//初始化Tab页
function initTabs(){
	$.post(URLMODULE.getAuctionTab+'?_' + Math.random(),function(data) {
		for(var key in TabsStatus){
			var count = getTabCountNumber(data,key);
			if(count!=null){
				$("#tabs").tabs('add', {
					id:key,
					title:TabsStatus[key]+'&nbsp;<font color="#fb6f00">' + count + '</font>',
					closable: false,
					selected: key=='0'?true:false
				});
			}
		}
    });
	$('#tabs').tabs({
	    onSelect:function(title,index){
    	    currentStatus = stateToIndex[title.substring(0,title.indexOf('&'))];
    	    currentStatus = currentStatus == '0'?'':stateToIndex[title.substring(0,title.indexOf('&'))];
    	    reLoadData(0);
	    }
	});
}	
//获取Tab页数据
function getTabCountNumber(data,key){
	var count=null;
	for(var i=0;i<data.length;i++){
		if(data[i].auction_state == key){//data数据与indexToStatus关联
			count = data[i].TAB_COUNT;
			break;
		}
	}
	return count;
}
//拍品状态
var TabsStatus = {0:'全部',1:'未上拍',2:'进入拍卖会',3:'待发布',4:'拍卖中',5:'成交',6:'售后',7:'完结'};
//选择标签
var stateToIndex = {'全部':0,'未上拍':1,'进入拍卖会':2,'待发布':3,'拍卖中':4,'成交':5,'售后':6,'完结':7};
//更新Tab页
function updateTab(type){
	$.post(URLMODULE.getAuctionTab+'?_' + Math.random(),
		{
			auctionType:$('#tb-auctionType').combobox('getValue'),
			subCompanyAuditId:$('#tb-auditState').combobox('getValue'),
			setStateId: $('#tb-auctionSet').combobox('getValue'),
			paimaiDateStart: $('#tb-paimaiDateStart').datetimebox("getValue"),
			paimaiDateEnd: $('#tb-paimaiDateEnd').datetimebox("getValue"),
			registStateId: $('#tb-registState').combobox('getValue'),
			keywords: $('#searchText').searchbox('getValue').trim()
		},
		function(data) {
		var tabs = $('#tabs').tabs('tabs');
		var index=0;
		for(var key in TabsStatus){
			var count = getTabCountNumber(data,key);
			if(count!=null){
				$("#tabs").tabs('update', {
					tab: tabs[index],
					options: {
						title:TabsStatus[key]+'&nbsp;<font color="#fb6f00">&nbsp;' + count + '</font>'
	            	}
				});
				index=index+1;
			}
		}
		initReLoadData(type);				
    });
}
//查看
function getDetails(carCode) {
	window.location.href="../../html/auction/auctionDetails.html?id="+carCode;
}
//编辑
function editor(carCode) {
	window.location.href="../../html/auction/editorAuction.html?id="+carCode;
}
//拍品信息审核
function auctionAudit(carCode) {
	window.location.href="../../html/auction/auctionSingleAudit.html?id="+carCode;
}
//参拍设置
function auctionSet(carCode) {
	window.location.href="../../html/auction/auctionSet.html?id="+carCode;
}
