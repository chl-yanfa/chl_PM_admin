//流拍管理列表页
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
        url : URLMODULE.getAbortiveList,
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
                {title : '操作', field : 'id', width : 40, align : 'center',formatter:function(value,row,index){
					var auctionId = value;
					var auctionSetId = row.auctionSetId;
					var abortiveState = row.abortiveStateFlag;
					var doMethod="doMethod('"+auctionId+"','"+auctionSetId+"','"+abortiveState+"')";
					var optionStr = '';
					var styleStr='href="javascript:void(0);" style="font-size: 14px;color: #4EA2FF;"';
					optionStr += '<a '+styleStr+' onclick="'+doMethod+'">处理</a><br>';
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
	$('#tb-dealDateStart').datetimebox({
		editable : false,
		width : 150,
		showSeconds : false
	});
	$('#tb-dealDateEnd').datetimebox({
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
	//自动将搜索框中的小写字母转成大写
	$("input.searchbox-text").change(function(){
		$(this).val($(this).val().toUpperCase());
	});
	$('#iconSearch').click(function() {
		 updateTab(0);
	});
	$('#iconReset').click(function() {
		$('#tb-auctionType').combobox('clear');
		$('#tb-dealDateStart').datetimebox('clear');
		$('#tb-dealDateEnd').datetimebox('clear');
		$('#searchText').searchbox('clear');
		updateTab(0);
	});
}
//重新加载表格数据
function reLoadData(page){
	var loadType = page==1?"reload":"load";
	$("#list").datagrid(loadType, {
		auctionType:$('#tb-auctionType').combobox('getValue'),
		dealDateStart: $('#tb-dealDateStart').datetimebox("getValue"),
		dealDateEnd: $('#tb-dealDateEnd').datetimebox("getValue"),
		keywords: $('#searchText').searchbox('getValue').trim(),
		abortiveState: currentStatus
    });
}

//初始化Tab页
function initTabs(){
	$.post(URLMODULE.getAbortiveTab+'?_' + Math.random(),function(data) {
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
		if(data[i].abortive_state == key){//data数据与indexToStatus关联
			count = data[i].TAB_COUNT;
			break;
		}
	}
	return count;
}
//拍品状态
var TabsStatus = {0:'全部',1:'待处理',2:'待审核',3:'已驳回'};
var stateToIndex = {'全部':-9,'待处理':0,'待审核':1,'已驳回':-1};

//更新Tab页
function updateTab(type){
	$.post(URLMODULE.getAbortiveTab+'?_' + Math.random(),{
		auctionType:$('#tb-auctionType').combobox('getValue'),
		dealDateStart: $('#tb-dealDateStart').datetimebox("getValue"),
		dealDateEnd: $('#tb-dealDateEnd').datetimebox("getValue"),
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
function doMethod(auctionId,auctionSetId,abortiveState){
	window.location.href="../../html/aftersale/abortiveDetail.html?auctionId="+auctionId+"&auctionSetId="+auctionSetId;
}