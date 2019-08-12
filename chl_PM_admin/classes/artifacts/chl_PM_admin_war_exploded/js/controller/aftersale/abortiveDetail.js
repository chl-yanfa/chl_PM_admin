$(function() {
	addCjTable();
	addAuctionTable();
	loadHtml();
	loadAuctionData();
});
function loadHtml(){
	var auctionSetId = getParamValue("auctionSetId");
	var auctionId = getParamValue("auctionId");
	$.post('../../paimai/getPaimaiInfo',{
		auctionSetId:auctionSetId
	},function(res) {
		var paimaiInfo=res.entity;
		if(paimaiInfo!=null){
			$(".paimaiInfo-div").find("span[col]").each(function(){
				$(this).html(paimaiInfo[$(this).attr("col")]);
			});
		}
	});
	var str=
	'<div class="c-l-center">'+
		'<label>已流拍</label>'+
	'</div>'+
	'<div class="c-l-bottom">'+
		'<button onclick="auctionAgain()" class="c-l-btn">再次拍卖</button>'+
	'</div>';
	$('.c-l-body').html(str);		
}
function addCjTable(){
	var auctionSetId = getParamValue("auctionSetId");
	$.post('../../offerwater/getOfferWaterList',{
		auctionSetId:auctionSetId
	},function(res) {
		if(res.returnCode=='000000'){
			var str='';
			for(var i=0;i<res.rows.length;i++){
				var cjInfo = res.rows[i];
				str+='<tr>'+
				'<td>'+cjInfo.nickname+'</td>'+
				'<td>'+cjInfo.bidTime+'</td>'+
				'<td>'+cjInfo.bidAmount+'</td>'+
				'</tr>';
			}
			$('.cjTable').append(str);
		}
	});
	$.post('../../offerwater/getBidCountByOrderId',{
		auctionSetId:auctionSetId
	},function(res) {
		if(res.returnCode=='000000'){
			$('#bidCount').html(res.entity);
		}
	});
}
function addAuctionTable(){
	var auctionSetId = getParamValue("auctionSetId");
	$.post('../../offerwater/getHighestCustomerByOrderId',{
		auctionSetId:auctionSetId
	},function(res) {
		if(res.returnCode=='000000'&&res.entity!=null){
			var str='';
			var info = res.entity;
			str+='<tr>'+
			'<td>'+info.mid+'</td>'+
			'<td>'+info.nickname+'</td>'+
			'<td>'+info.phone+'</td>'+
			'<td>'+info.maximumPrice+'</td>'+
			'</tr>';
			$('.auctionTable').append(str);
			highestCustomer=info;
			$('#highestPrice').html(info.maximumPrice);
		}
	});
}
function auctionAgain(){
	var auctionId = getParamValue("auctionId");
	var auctionSetId = getParamValue("auctionSetId");
	window.location.href="../../html/aftersale/auctionAgain.html?auctionId="+auctionId+"&auctionSetId="+auctionSetId+"&type=LP";;
}
function secondAsk(){
	var auctionId = getParamValue("auctionId");
	window.location.href="../../html/aftersale/secondAsk.html?id="+auctionId;
}
function scrapVehicle(){
	var auctionId = getParamValue("auctionId");
	window.location.href="../../html/aftersale/scrap.html?id="+auctionId;
}
function loadAuctionData(){
	var auctionId = getParamValue("auctionId");
	var auctionSetId = getParamValue("auctionSetId");
	$("#auctionSetBody").load("../auction/auctionSetTemp.html",function(){
		$("#vehicleBody").load("../auction/auctionTemp.html",function(){
			$.post("../../auction/getVPWById",{
				id : auctionId,
			},function(res) {
				var auction=res.entity.auction;
				var procedure=res.entity.procedure;
				var warehouse=res.entity.warehouse;
				
				loadAuctionDetail("#vehicleInfo","#carPhotoUrl",auction);
				$.post('../../auctionSet/getAuctionSetById',{
					auctionSetId:auctionSetId
				},function(res) {
					var auctionSet=res.entity;
					$("#auctionSetDiv table").find("span[col]").each(function(){
						$(this).html(auctionSet[$(this).attr("col")]);
					});
				});
			});
		});
	});
}
