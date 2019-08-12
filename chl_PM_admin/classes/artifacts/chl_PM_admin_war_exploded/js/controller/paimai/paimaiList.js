//拍卖会列表
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
        url : URLMODULE.getPaimaiList,
        method : 'post',
        autoLoad:false,
        idField : 'id',
        view:tcwOrderInfoView,
        columns : [[
            {title : '拍卖会ID', field : 'pmhId', width : 80, align : 'center',halign:'center'},
            {title : '拍卖会名称', field : 'paimaiName', width : 120, align : 'center'},
            {title : '拍卖开始时间', field : 'paimaiStartTime', width : 80, align : 'center'},
            {title : '拍卖结束时间',field: 'paimaiFinishTime',width:80,align:'center'},
            {title : '拍品数量',field:'auctionCount',width:50,align:'center'},
            {title : '拍卖状态',field:'paimaiStates',width:50,align:'center'},
            {title : '成交数量',field:'dealCount',width:50,align:'center'},
            {title : '发布时间', field : 'publishTime', width : 80,align:'center'},
            {title : '操作', field : 'id', width : 100, align : 'center',formatter:function(value,row,index){
            	var paimaiId=value;
            	var getDetail="getDetail('"+paimaiId+"')";/*查看*/
            	var editor="editor('"+paimaiId+"')";/*编辑*/
            	var publishEditor="publishEditor('"+paimaiId+"')";/*发布后编辑*/
            	var deleteThis="deleteThis('"+paimaiId+"')";/*删除*/
            	var publish="publish('"+paimaiId+"')";/*发布*/
            	var styleStr='href="javascript:void(0);" style="font-size: 14px;color: #4EA2FF;"';
            	var splitStr='&nbsp;<font style="color: #4EA2FF;">|</font>&nbsp;';
            	var optionStr = '';
            	if(row.paimaiState==0&&row.auctionCount==0){
            		optionStr += '<a '+styleStr+' onclick="'+editor+'">编辑</a>'+splitStr;
            		optionStr += '<a '+styleStr+' onclick="'+deleteThis+'">删除</a>';
            	}else if(row.paimaiState==0&&row.auctionCount!=0){
            		optionStr += '<a '+styleStr+' onclick="'+editor+'">编辑</a>'+splitStr;
            		optionStr += '<a '+styleStr+' onclick="'+publish+'">发布</a>';
            	}else if(row.paimaiState==1){
            		optionStr += '<a '+styleStr+' onclick="'+publishEditor+'">编辑</a>';
            	}else{
            		optionStr += '<a '+styleStr+' onclick="'+getDetail+'">查看</a>';
            	}
                return optionStr;
           }}
        ]],
        toolbar : toolbarType,
        pagination : true,
        singleSelect : true,
        fitStyle : true
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
	
	$('#searchText').searchbox({
	   width :222,
	   prompt : '拍卖会名称',
	   searcher:function(value,name){
		   $('#searchText').searchbox("setValue",value.toUpperCase());
		   reLoadData(0);
	   }
	});
	
	$('#iconSearch').click(function() {
		updateTab(0);
	});
	$('#iconReset').click(function() {
		$('#tb-paimaiDateStart').datetimebox('clear');
		$('#tb-paimaiDateEnd').datetimebox('clear');
		$('#searchText').searchbox('clear');
		updateTab(0);
	});
	$('#iconAdd').click(function() {
		window.location.href="../../html/paimai/addPaimai.html";
	});
}
//重新加载表格数据
function initReLoadData(type){
	var loadType = type==1?"reload":"load";
	$("#list").datagrid(loadType, {
		paimaiDateStart: $('#tb-paimaiDateStart').datetimebox("getValue"),
		paimaiDateEnd: $('#tb-paimaiDateEnd').datetimebox("getValue"),
		keywords: $('#searchText').searchbox('getValue').trim(),
		paimaiState: currentStatus
  });
}
function reLoadData(type){
	updateTab(type);
}
var currentStatus='';
//初始化Tab页
function initTabs(){
	$.post(URLMODULE.getPaimaiTab+'?_' + Math.random(),function(data) {
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
    	    currentStatus = currentStatus == '-1'?'':stateToIndex[title.substring(0,title.indexOf('&'))];
    	    reLoadData(1);
	    }
	});
}	
//获取Tab页数据
function getTabCountNumber(data,key){
	var count=null;
	for(var i=0;i<data.length;i++){
		if(data[i].paimai_state == key){//data数据与indexToStatus关联
			count = data[i].TAB_COUNT;
			break;
		}
	}
	return count;
}
//拍品状态
var TabsStatus = {0:'全部',1:'待发布',2:'进行中',3:'已结束'};
//选择标签
var stateToIndex = {'全部':-1,'待发布':0,'进行中':1,'已结束':2};
//更新Tab页
function updateTab(type){
	$.post(URLMODULE.getPaimaiTab+'?_' + Math.random(),
		{
			paimaiDateStart: $('#tb-paimaiDateStart').datetimebox("getValue"),
			paimaiDateEnd: $('#tb-paimaiDateEnd').datetimebox("getValue"),
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
function getDetail(paimaiId){
	window.location.href="../../html/paimai/paimaiDetail.html?id="+paimaiId;
}
function editor(paimaiId){
	window.location.href="../../html/paimai/paimaiEditor.html?id="+paimaiId;
}
function publishEditor(paimaiId){
	window.location.href="../../html/paimai/paimaiPublish.html?id="+paimaiId;
}
function deleteThis(paimaiId){
	$.messager.confirm('提示', '确定删除吗?', function(r){
		if (r){
			$.post('../../paimai/deletePaimai',{
				id : paimaiId
			},function(data) {
				$.messager.alert('提示', "删除成功");
				updateTab(0);
			});
		}
	});
}
function publish(paimaiId){
	$.post('../../paimai/publishPaimai',{
		paimaiId : paimaiId
	},function(res) {
		if(res.returnCode=='000000'){
			$.messager.alert('提示', "发布成功");
		}else{
			$.messager.alert('提示', "发布失败");
		}
		updateTab(0);
	});
}
