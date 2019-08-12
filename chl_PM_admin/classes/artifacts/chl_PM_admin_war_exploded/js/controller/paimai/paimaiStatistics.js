$(function() {
	loadPaimai();
	loadTable();
});
function loadPaimai(){
	var paimaiId = getParamValue("id");
	$.post('../../paimai/getPaimaiById',{
		id : paimaiId
	},function(res) {
		var paimai=res.entity;
		$(".paimai-top").find("span[col]").each(function(){
			$(this).html(paimai[$(this).attr("col")]);
		});
	});
}
function loadTable(){
	var paimaiId = getParamValue("id");
	$.post('../../paimai/getPaimaiStatistics',{
		paimaiId : paimaiId
	},function(res) {
		var entity=res.entity;
		var vehicleList=entity.vehicleList;
		var goodsList=entity.goodsList;
		var paimaiHot=entity.paimaiHot;
		var allStatistics=entity.allStatistics;
		if(vehicleList!=null&&vehicleList.length>0){
			var str='';
			for(var i in vehicleList){
				var vehicle=vehicleList[i];
				str+='<tr>'+
				'<td>'+vehicle.lotAreas+'</td>'+
				'<td>'+vehicle.auctionCount+'</td>'+
				'<td>'+vehicle.dealCount+'('+vehicle.dealPercent+'%)</td>'+
				'<td>'+vehicle.hasPriceCount+'('+vehicle.hasPricePercent+'%)</td>'+
				'<td>'+vehicle.hasNoPriceCount+'('+vehicle.hasNoPricePercent+'%)</td>'+
				'<td>'+vehicle.abortiveCount+'('+vehicle.abortivePercent+'%)</td>'+
				'<td>'+vehicle.aveDealPrice+'</td>'+
				'<td>'+vehicle.aveProfit+'</td>'+
				'<td>'+vehicle.totalProfit+'</td>'+
				'</tr>';
			}
			$('.vehicle-table').append(str);
		}
		if(goodsList!=null&&goodsList.length>0){
			var str='';
			for(var i in goodsList){
				var goods=goodsList[i];
				str+='<tr>'+
				'<td>'+goods.lotAreas+'</td>'+
				'<td>'+goods.auctionCount+'</td>'+
				'<td>'+goods.dealCount+'('+goods.dealPercent+'%)</td>'+
				'<td>'+goods.hasPriceCount+'('+goods.hasPricePercent+'%)</td>'+
				'<td>'+goods.hasNoPriceCount+'('+goods.hasNoPricePercent+'%)</td>'+
				'<td>'+goods.abortiveCount+'('+goods.abortivePercent+'%)</td>'+
				'<td>'+goods.aveDealPrice+'</td>'+
				'<td>'+goods.aveProfit+'</td>'+
				'<td>'+goods.totalProfit+'</td>'+
				'</tr>';
			}
			$('.goods-table').append(str);
		}
		if(allStatistics!=null){
			var str='';
			str+='<tr>'+
			'<td style="width: 100px;">总合计</td>'+
			'<td style="width: 100px;">'+allStatistics.auctionCount+'</td>'+
			'<td style="width: 100px;">'+allStatistics.dealCount+'('+allStatistics.dealPercent+'%)</td>'+
			'<td style="width: 100px;">'+allStatistics.hasPriceCount+'('+allStatistics.hasPricePercent+'%)</td>'+
			'<td style="width: 100px;">'+allStatistics.hasNoPriceCount+'('+allStatistics.hasNoPricePercent+'%)</td>'+
			'<td style="width: 100px;">'+allStatistics.abortiveCount+'('+allStatistics.abortivePercent+'%)</td>'+
			'<td style="width: 100px;">'+allStatistics.aveDealPrice+'</td>'+
			'<td style="width: 100px;">'+allStatistics.aveProfit+'</td>'+
			'<td style="width: 100px;">'+allStatistics.totalProfit+'</td>'+
			'</tr>';
			$('.all-table').append(str);
		}
		if(paimaiHot!=null){
			$(".paimaiHot-table").find("td[col]").each(function(){
				$(this).html(paimaiHot[$(this).attr("col")]);
			});
		}
	});
}

