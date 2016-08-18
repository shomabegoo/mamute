$(".vote-option").bind("click", function() {
	if(!$(this).hasClass("stop")) {
		if (!$(this).hasClass("voted")) {
			$(this).addClass("stop");
			vote($(this));
		}
		else if ($(this).hasClass("voted")) {
			$(this).addClass("stop");
			removeVote($(this));
		}
	}
	
});

function vote(link) {
	var vote = link.data("value");
	var type = link.data("type");
	var id = link.data("id");
	var params = "/"+ type +"/"+ id +"/voto/"+ vote;

	fakeUpdateIncrement(link, vote);
	
	$.ajax(""+ params, {
		complete: function(jqXHR, textStatus) {
			if (jqXHR.status == "200") {
				var count = jqXHR.responseText;
				voteSuccess(link, count);
			} else if (jqXHR.status == "409") {
				errorPopup(Messages.get('vote.ownquestion'), link);
			} else if (jqXHR.status == "403") {
				errorPopup(jqXHR.responseText, link);
			} else {
				errorPopup(Messages.get('error.occured'), link);
				console.log(jqXHR);
			}
			$(".vote-option").removeClass("stop");
		},
		accepts: "application/json",
		method: "POST"
	});
	
}

function removeVote(link) {
	var vote = link.data("value");
	var type = link.data("type");
	var id = link.data("id");
	var params = "/"+ type +"/"+ id +"/voto/remove/"+ vote;
	
	fakeUpdateDecrement(link, vote);
	
	$.ajax(""+ params, {
		complete: function(jqXHR, textStatus) {
			if (jqXHR.status == "200") {
				var count = jqXHR.responseText;
				voteRemovalSuccess(link, count);
			} else if (jqXHR.status == "409") {
				errorPopup(Messages.get('vote.ownquestion'), link);
			} else if (jqXHR.status == "403") {
				errorPopup(jqXHR.responseText, link);
			} else {
				errorPopup(Messages.get('error.occured'), link);
				console.log(jqXHR);
			}
			$(".vote-option").removeClass("stop");
		},
		accepts: "application/json",
		method: "POST"
	});
}

function highlight(link) {
	link.addClass("voted").siblings().removeClass("voted");
}

function removeHighlight(link) {
	link.removeClass("voted").siblings().removeClass("voted");
}

function updateCount(link, count) {
	var voteCount = $(link).closest(".vote-container").find(".vote-count");
	voteCount.text(count.toString().toPersianDigits());
}

function fakeUpdateIncrement(link, vote) {
	var value;
	var countWithoutAjax = parseInt($(link).closest(".vote-container").find(".vote-count").html().toEnglishDigits());
	
	if(vote == "positivo") value = countWithoutAjax + 1;
	else value = countWithoutAjax - 1;
	
	voteSuccess(link, value);
}

function fakeUpdateDecrement(link, vote) {
	var value;
	var countWithoutAjax = parseInt($(link).closest(".vote-container").find(".vote-count").html().toEnglishDigits());
	
	if(vote == "positivo") value = countWithoutAjax - 1;
	else value = countWithoutAjax + 1;
	
	voteRemovalSuccess(link, value);
}


function voteSuccess(link, count) {
	highlight(link);
	updateCount(link, count);
}

function voteRemovalSuccess(link, count) {
	removeHighlight(link);
	updateCount(link, count);
}

String.prototype.toEnglishDigits = function () {
    var charCodeZero = '۰'.charCodeAt(0);
    var v1 = parseInt(this.replace(/[۰-۹]/g, function (w) {
        return w.charCodeAt(0) - charCodeZero;
    }));

    var charCodeZeroIndia = '٠'.charCodeAt(0);
    var v2 = parseInt(this.replace(/[٠-٩]/g, function (w) {
         return w.charCodeAt(0) - charCodeZeroIndia;
    }));

    return v1 | v2;
}

String.prototype.toPersianDigits = function(){
 var id= ['۰','۱','۲','۳','۴','۵','۶','۷','۸','۹'];
 return this.replace(/[0-9]/g, function(w){
  return id[+w]
 });
}
