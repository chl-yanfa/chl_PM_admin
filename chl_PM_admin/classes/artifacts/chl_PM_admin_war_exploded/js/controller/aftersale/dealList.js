//成交管理列表页
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
        nowrap : true,
        striped : true,
        fit : false,
        fitColumns : true,
        url : URLMODULE.getDealList,
        method : 'post',
        autoLoad:false,
        idField : 'id',
        view:tcwOrderInfoView,
        rowTitleFormatter:function(row){
        	return "拍品编码："+row['auctionNo'];
        },
        columns : [[
                {title : '拍品基本信息', field : 'carCode', width : 260, align : 'left',halign:'center',formatter: function(value,rec,index){
                	return carHeadInfo(rec);
                }},
                {title:'拍品状态',field:'vehicleState',width:60,align:'center'},
                {title : '成交时间', field : 'dealTime', width : 80,align:'center'},
                {title:'付款状态',field:'payState',width:60,align:'center'},
                {title : '提货状态', field : 'takeCarState', width : 60,align:'center'},
                {title : '操作', field : 'id', width : 120, align : 'center',formatter:function(value,row,index){
					var auctionId = value;
					var auctionSetId = row.auctionSetId;
					var processTrace="processTrace('"+auctionId+"','"+auctionSetId+"')";
					var optionStr = '';
					var styleStr='href="javascript:void(0);" style="font-size: 14px;color: #4EA2FF;"';
					optionStr += '<a '+styleStr+' onclick="'+processTrace+'">操作</a><br>';
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
    	width : 150,
		valueField:'id',
		textField:'value',
		data: [{id:"0",value: "全车配件"},{id:"1",value: "高价值配件"},{id:"2",value: "大宗配件"}],
		panelHeight:"auto"
	});
	//拍卖时间
	$('#tb-paimaiDateStart').datetimebox({
		editable : false,
		width : 150,
		showSeconds : false
	});
	$('#tb-paimaiDateEnd').datetimebox({
		editable : false,
		width : 150,
		showSeconds : false
	});
	$('#searchText').searchbox({
	   width :280,
	   prompt : '车牌号/品牌型号',
	   searcher:function(value,name){
		   $('#searchText').searchbox("setValue",value.toUpperCase());
		   updateTab(0);
	   }
	});
	$('#iconSearch').click(function() {
		 updateTab(0);
	});
	$('#iconReset').click(function() {
		$('#tb-auctionType').combobox('clear');
		$('#tb-paimaiDateStart').datetimebox('clear');
		$('#tb-paimaiDateEnd').datetimebox('clear');
		$('#searchText').searchbox('clear');
		updateTab(0);
	});
}
//重新加载表格数据
function reLoadData(page){
	var loadType = page==1?"reload":"load";
	$("#list").datagrid(loadType, {
		auctionType:$('#tb-auctionType').combobox('getValue'),
		paimaiDateStart: $('#tb-paimaiDateStart').datetimebox("getValue"),
		paimaiDateEnd: $('#tb-paimaiDateEnd').datetimebox("getValue"),
		keywords: $('#searchText').searchbox('getValue').trim(),
		aftersaleState: currentStatus
    });
}

//初始化Tab页
function initTabs(){
	$.post(URLMODULE.getDealTab+'?_' + Math.random(),function(data) {
		for(var key in TabsStatus){
			var count = getTabCountNumber(data,key);//key即为tabsStatus编号
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
    	    currentStatus = currentStatus == '-9'?'':stateToIndex[title.substring(0,title.indexOf('&'))];
			reLoadData(0);
	    }
	});
}	
//获取Tab页数据
function getTabCountNumber(data,key){
	var count=null;
	for(var i=0;i<data.length;i++){
		if(data[i].aftersale_state == key){//data数据与indexToStatus关联
			count = data[i].TAB_COUNT;
			break;
		}
	}
	return count;
}
//拍品状态
var TabsStatus = {0:'全部',1:'待付款',2:'已付款',3:'待提货',4:'已提货',5:'待过户',6:'已过户',7:'延期未付款',8:'延期未提货',9:'延期未过户'};
var stateToIndex = {'全部':-9,'待付款':0,'已付款':1,'待提货':2,'已提货':3,'待过户':4,'已过户':5,'延期未付款':-1,'延期未提货':-3,'延期未过户':-5};

//更新Tab页
function updateTab(type){
	$.post(URLMODULE.getDealTab+'?_' + Math.random(),{
		auctionType:$('#tb-auctionType').combobox('getValue'),
		paimaiDateStart: $('#tb-paimaiDateStart').datetimebox("getValue"),
		paimaiDateEnd: $('#tb-paimaiDateEnd').datetimebox("getValue"),
		keywords: $('#searchText').searchbox('getValue').trim()
	},function(data) {
		var tabs = $('#tabs').tabs('tabs');
		var index = 0;
		for ( var key in TabsStatus) {
			var count = getTabCountNumber(data, key);
			if (count != null) {
				$("#tabs").tabs('update',{
					tab : tabs[index],
					options : {
						title : TabsStatus[key]+ '&nbsp;<font color="#fb6f00">&nbsp;'+ count + '</font>'
					}
				});
				index = index + 1;
			}
		}
		reLoadData(type);			
	});
}
function processTrace(auctionId,auctionSetId){
	window.location.href="../../html/aftersale/aftersaleTrace.html?id="+auctionId+"&auctionSetId="+auctionSetId;
}