/* global regions */

var _ctx = $("meta[name='ctx']").attr("content");

$(document).ready(function () {
    setMenuActive("report-questionnaire");
    $.getJSON(_ctx + "/rest/report/questionnaireInfo", function (report) {
        $('#questionnaireCount1').text(format(report[0][1]));
        $('#questionnaireCount2').text(format(report[0][2]));
    });
    var template = $('#template');
    var last = template;
    for (var i in regions) {
        var c = template.clone();
        c.find('#questionnaireCount1').attr('id', 'questionnaireCount1-' + i);
        c.find('#questionnaireCount2').attr('id', 'questionnaireCount2-' + i);
        c.find('.regionName').text(regions[i]);
        last.after(c);
        last = c;
    }
    for (var i in regions) {
        (function (i, region) {
            $.getJSON(_ctx + "/rest/report/questionnaireInfoRegion?region=" + i, function (report) {
                $('#questionnaireCount1-' + i).text(format(report[0][2]));
                $('#questionnaireCount2-' + i).text(format(report[0][3]));
            });
        })(i, regions[i]);
    }
});
