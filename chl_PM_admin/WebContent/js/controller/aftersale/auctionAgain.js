$(function() {
	$('#sumbitAuctionSet').click(function() {
		var type = getParamValue("type");
		if(type=="LP"){
			submitSet();
		}else if(type =="TC"){
			submitTCSet();
		}
	});
});

function submitSet(){
	if (!$('#auctionAgain').form("validate")) {
		return;
	}
	var auctionId= getParamValue("auctionId");
	var ff = "";
	ff = $('#auctionAgain input,#auctionAgain textarea').serialize();
	ff += "&auctionId=" + auctionId;
	$.post("../../auctionSet/auctionAgainSet",ff,function(data) {
		if (data.returnCode == "000000") {
			$.messager.alert('提示','参拍设置成功','info',function(){
				var auctionSetId = getParamValue("auctionSetId");
				window.location.href="../../html/aftersale/abortiveAuctionList.html";
			});
		}else {
			$.messager.alert('提示', "参拍设置失败");
		}
	});
}
function submitTCSet(){
	if (!$('#auctionAgain').form("validate")) {
		return;
	}
	var auctionId= getParamValue("auctionId");
	var ff = "";
	ff = $('#auctionAgain input,#auctionAgain textarea').serialize();
	ff += "&auctionId=" + auctionId;
	$.post("../../auctionSet/auctionAgainSetTC",ff,function(data) {
		if (data.returnCode == "000000") {
			$.messager.alert('提示','参拍设置成功','info',function(){
				goBackView(-1);
			});
		}else {
			$.messager.alert('提示', "参拍设置失败");
		}
	});
}
function cancel(){
	window.history.back(-1);
	window.parent.scrollTo(0, 0);
}