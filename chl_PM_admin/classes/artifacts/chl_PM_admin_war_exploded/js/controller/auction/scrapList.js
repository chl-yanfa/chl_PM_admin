$(document).ready(function(){
	//初始化表格数据
	initDateGrid();
	//初始化Tab页数据
	initTabs();
});
var currentStatus='';
//初始化数据表格
function initDateGrid(){
	$("#scrapCarList").datagrid({
    	loadMsg : "数据加载中,请稍后……",
    	nowrap : false,/*换行*/
        striped : true,
        fit : false,
        fitColumns : true,
        url : URLMODULE.getScrapCarList,
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
            /*{title : '在库手续', field : 'procedureFileList', width : 200, align : 'center',formatter:function(value,row,index){
            	if(value!=null&&value.length>0){
            		var str='<div class="inProcedure">';
            		for(var key in fileNameList){
            			var res=getProcedureName(key,value);
            			if(res==0){
            				str+='<div class="proce-div"><img src="../../resources/images/has.png" width="15px" height="15px"/>'
            					+'<label>'+fileNameList[key]+'</label></div>';
            			}else if(res==1){
            				str+='<div class="proce-div"><span class="redCircle"><span class="sline">‐</span></span>'
            					+'<label>'+fileNameList[key]+'</label></div>';
            			}else if(res==-1){
            				str+='<div class="proce-div"><img src="../../resources/images/no.png" width="15px" height="15px"/>'
            					+'<label>'+fileNameList[key]+'</label></div>';
            			}
            		}
            		str+='</div>';
            		return str;
            	}else{
            		var str='<div class="inProcedure">';
            		for(var i in fileNameList){
            			str+='<div class="proce-div"><img src="../../resources/images/no.png" width="15px" height="15px"/>'
        					+'<label>'+fileNameList[i]+'</label></div>';
            		}
            		str+='</div>';
            		return str;
            	}
            }},*/
            {title:'提交报废时间',field:'submitTime',width:50,align:'center'},
            {title : '提交人', field : 'submitUser', width : 30,align:'center'},
            {title:'报废状态',field:'scrapState',width:30,align:'center'},
            {title:'确认报废时间',field:'scrapTime',width:50,align:'center'},
            {title : '操作', field : 'id', width : 40, align : 'center',formatter:function(value,row,index){
            	var auctionId=value;
            	var confirmScrap="confirmScrap('"+auctionId+"')";
            	var optionStr = '';
                if(row.scrapStateFlag==0){
                	optionStr += '<a href="javascript:void(0);" style="font-size: 14px;color: #4EA2FF;" onclick="'+confirmScrap+'">确认报废</a>&nbsp;&nbsp;';
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
    	width : 150,
		valueField:'id',
		textField:'value',
		data: [{id:"0",value: "全车配件"},{id:"1",value: "高价值配件"},{id:"2",value: "大宗配件"}],
		panelHeight:"auto"
	});
	$('#searchText').searchbox({
	   width :222,
	   prompt : '车牌号/品牌型号',
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
		$('#searchText').searchbox('clear');
		updateTab(0);
	});
}
var fileNameIndex={0:2,1:4,2:8,3:6,4:12,5:0,6:1}
var fileNameList={0:'登记证',1:'行驶证',2:'购置税',3:'购车发票',4:'委托方组织机构代码',5:'车主身份证',6:'委托合同'}
function getProcedureName(key,fileList){
	var flag=-1;
	for(var i=0;i<fileList.length;i++){
		if(fileNameIndex[key]==fileList[i].fileId){
			if(fileList[i].procedureState==0){//入库
				flag=0;
			}else{
				flag=1;
			}
			break;
		}
	}
	return flag;
}
//重新加载表格数据
function reLoadData(page){
	var loadType = page==1?"reload":"load";
	$("#scrapCarList").datagrid(loadType, {
		auctionType:$('#tb-auctionType').combobox('getValue'),
		keywords: $('#searchText').searchbox('getValue').trim(),
		scrapState: currentStatus
    });
}

//初始化Tab页
function initTabs(){
	$.post(URLMODULE.getScrapCarTab+'?_' + Math.random(),function(data) {
		for(var key in TabsStatus){
			var count = getATabCountNumber(data,key);//key即为tabsStatus编号
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
			reLoadData(1);
	    }
	});
}	

//获取Tab页数据
function getATabCountNumber(data,status){
	var count=null;
	for(var i=0;i<data.length;i++){
		if(data[i].auction_state == status){//data数据与indexToStatus关联
			count = data[i].TAB_COUNT;
			break;
		}
	}
	return count;
}
//拍品状态
//选择标签
var TabsStatus = {0:'全部',1:'待报废',2:'已报废'};
var stateToIndex = {'全部':-9,'待报废':0,'已报废':1};
//更新Tab页
function updateTab(type){
	$.post(URLMODULE.getScrapCarTab+'?_' + Math.random(),{
		auctionType:$('#tb-auctionType').combobox('getValue'),
		keywords: $('#searchText').searchbox('getValue').trim()
	},
	function(data) {
	var tabs = $('#tabs').tabs('tabs');
	var index=0;
	for(var key in TabsStatus){
		var count = getATabCountNumber(data,key);
		if(count!=null){
			$("#tabs").tabs('update', {
				tab: tabs[index],
				options: {
					title:TabsStatus[key]+'&nbsp;<font color="#fb6f00">' + count + '</font>'
            	}
			});
			index=index+1;
		}
	}
	reLoadData(type);			
  });
}

//确认报废
function confirmScrap(auctionId) {
	window.location.href="../../html/auction/confirmScrap.html?id="+auctionId;
}
