//用户管理列表
$(document).ready(function(){
	//初始化表格数据
	initDateGrid();
	reLoadData(0);
});
//初始化数据表格
function initDateGrid(){
	$("#list").datagrid({
    	loadMsg : "数据加载中,请稍后……",
        nowrap : true,
        striped : true,
        fit : false,
        fitColumns : true,
        url : URLMODULE.getCustomerList,
        method : 'post',
        autoLoad:false,
        idField : 'id',
        view:tcwOrderInfoView,
        columns : [[
        		/*{title : '用户编码', field : 'none', width : 30, align : 'center', halign:'center',formatter:function(value,row,index){
                	return row.id;
                }},*/
                {title : '用户名称', field : 'userName', width : 50, align : 'center', halign:'center',formatter:function(value,row,index){
                	if(value==null){
                		return row.loginName;
                	}
                	return value;
                }},
                {title : '联系方式', field : 'phone', width : 50, align:'center', halign:'center'},
                {title : '实名状态', field : 'certificationStateS', width : 30, align:'center', halign:'center'},
                {title : '保证金', field : 'walletPledge', width : 30, align:'center', halign:'center'},
                {title : '冻结保证金', field : 'walletPledgeFreeze', width : 30, align:'center', halign:'center'},
                {title : '账户状态', field : 'statusStr', width : 30, align:'center', halign:'center'},
                {title : '操作', field : 'id', width : 100, align : 'center', halign:'center',
                	formatter:function(value,row,index){
	                	var id = value;
	                	var userName = row.userName;
	                	if(userName == null){
	                		userName = row.loginName;
	                	}
	                	var editorDialog="editorDialog('"+id+"')";/*编辑*/
	                	var resetPwdDialog="resetPwdDialog('"+id+"')";
	                	var frozenAccount="frozenAccount('"+id+"','"+userName+"','"+row.phone+"')";
	                	var thawAccount="thawAccount('"+id+"','"+userName+"','"+row.phone+"')";
	                	var chargeMarginDialog="chargeMarginDialog('"+id+"')";
	                	var marginWater="marginWater('"+id+"')";
	                	var styleStr='href="javascript:void(0);" style="font-size: 14px;color: #4EA2FF;"';
	                	var optionStr = '';
	                	optionStr += '<a '+styleStr+' onclick="'+editorDialog+'">编辑</a>&nbsp;&nbsp;&nbsp;&nbsp;';
	                	optionStr += '<a '+styleStr+' onclick="'+resetPwdDialog+'">重置密码</a>&nbsp;&nbsp;&nbsp;&nbsp;';
	                	if(row.status!=null && row.status=='1'){
	                		optionStr += '<a '+styleStr+' onclick="'+frozenAccount+'">冻结账户</a>&nbsp;&nbsp;&nbsp;&nbsp;';	                		
	                	}else if(row.status!=null && row.status=='3'){
	                		optionStr += '<a '+styleStr+' onclick="'+thawAccount+'">解冻账户</a>&nbsp;&nbsp;&nbsp;&nbsp;';
	                	}
	                	optionStr += '<a '+styleStr+' onclick="'+chargeMarginDialog+'">充值保证金</a>&nbsp;&nbsp;&nbsp;&nbsp;';
	                	optionStr += '<a '+styleStr+' onclick="'+marginWater+'">保证金流水</a>';
						return optionStr;
                	}
                }
              ]],
        toolbar : toolbarType,
        pagination : true,
        singleSelect : true,
        fitStyle : true
    });
	$('#searchText').searchbox({
	   width :280,
	   prompt : '用户姓名/手机号',
	   searcher:function(value,name){
		   $('#searchText').searchbox("setValue",value);
		   reLoadData(0);
	   }
	});
	$('#iconSearch').click(function() {
		reLoadData(0);
		$('#searchText').searchbox('clear');
	});
}

//重新加载表格数据
function reLoadData(page){
	var loadType = page==1?"reload":"load";
	$("#list").datagrid(loadType, {
		keywords: $('#searchText').searchbox('getValue').trim(),
    });
}
function editorDialog(id){
	$('#DIALOG').dialog({
		title : "修改会员信息",
		width : 400,
		height : 250,
		modal : true,
		href : "../../html/customer/customerInfo.html",
		onLoad: function () {
			$("#edit-div").show();
			$("#resetpwd-div").hide();
			$("#chargemargin-div").hide();
			$("#editForm input[name='id']").val(id);
		}
	});
}
function resetPwdDialog(id){
	$('#DIALOG').dialog({
		title : "重置密码",
		width : 400,
		height : 250,
		modal : true,
		href : "../../html/customer/customerInfo.html",
		onLoad: function () {
			$("#resetpwd-div").show();
			$("#edit-div").hide();
			$("#chargemargin-div").hide();
			$("#resetPwdForm input[name='id']").val(id);
		}
	});
}
function frozenAccount(id,userName,phone){
	$.messager.confirm('提示', '是否确定冻结该账户<br/>'+userName+'&nbsp;&nbsp;&nbsp;'+phone, function(r){
		if (r){
			$.post('../../customer/frozenAccount',{
				id:id
			},function(data) {
				if(data.returnCode=='000000'){
					reLoadData(0);
				}else{
					$.messager.alert('提示', data.returnMsg,'info');
				}
			});
		}
	});
}
function thawAccount(id,userName,phone){
	$.messager.confirm('提示', '是否确定解冻该账户<br/>'+userName+'&nbsp;&nbsp;&nbsp;'+phone, function(r){
		if (r){
			$.post('../../customer/thawAccount',{
				id:id
			},function(data) {
				if(data.returnCode=='000000'){
					reLoadData(0);
				}else{
					$.messager.alert('提示', data.returnMsg,'info');
				}
			});
		}
	});
}
function chargeMarginDialog(id){
	$('#DIALOG').dialog({
		title : "充值保证金",
		width : 400,
		height : 200,
		modal : true,
		href : "../../html/customer/customerInfo.html",
		onLoad: function () {
			$("#chargemargin-div").show();
			$("#resetpwd-div").hide();
			$("#edit-div").hide();
			$("#chargeMarginForm input[name='id']").val(id);
		}
	});
}
function marginWater(id){
	$('#DIALOG').dialog({
		title : "保证金流水",
		top:5,
		width : 1000,
		height : 600,
		modal : true,
		href : "../../html/customer/marginWater.html",
		onLoad: function () {
			initWaterDateGrid(id);
		},
		onClose: function () {
			reLoadData(0);
		}
	});
}
function editCustomer(){
	if (!$('#editForm').form("validate")) {
		return;
	}
	var dataJson = "";
	dataJson = $('#editForm input,#editForm textarea').serialize();
	$.post('../../customer/editCustomer',dataJson,function(data) {
		if(data.returnCode=='000000'){
			$('#DIALOG').dialog("close",true);
			reLoadData(1);
		}else{
			$.messager.alert('提示', data.returnMsg,'info');
		}
	});
}
function resetPwd(){
	if (!$('#resetPwdForm').form("validate")) {
		return;
	}
	var password = $("#resetPwdForm input[name='password']").val();
	var password2 = $("#resetPwdForm input[name='password2']").val();
	if(password!=password2){
		$.messager.alert('提示', '密码不一致','info');
		return;
	}
	var dataJson = "";
	dataJson = $('#resetPwdForm input,#resetPwdForm textarea').serialize();
	$.post('../../customer/resetPwd',dataJson,function(data) {
		if(data.returnCode=='000000'){
			$('#DIALOG').dialog("close",true);
			reLoadData(1);
		}else{
			$.messager.alert('提示', data.returnMsg,'info');
		}
	});
}
function chargeMargin(){
	if (!$('#chargeMarginForm').form("validate")) {
		return;
	}
	var id = $("#chargeMarginForm input[name='id']").val();
	var margin = $("#chargeMarginForm input[name='margin']").val();
	if(margin!=null && margin<=0){
		$.messager.alert('提示', '充值金额大于0','info');
		return;
	}
	$.post('../../customer/chargeMargin',{
		mid:id,
		chargeAmount:margin
	},function(data) {
		if(data.returnCode=='000000'){
			$('#DIALOG').dialog("close",true);
			reLoadData(1);
		}else{
			$.messager.alert('提示', data.returnMsg,'info');
		}
	});
}
function validNumber(obj){
	obj.value = obj.value.replace(/[^\d.]/g,"");
	obj.value = obj.value.replace(/^\./g,"");
	obj.value = obj.value.replace(/\.{2,}/g,".");
	obj.value = obj.value.replace(".","$#$").replace(/\./g,"").replace("$#$",".");
}
