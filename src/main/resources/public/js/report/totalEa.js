$(function () {
    setMenuActive("report-total-ea");
    $.getJSON(ctx + "/rest/report/total", function (report) {
        $('#eaFieldwork').text(format(report[0][1]));
        $('#eaFreshlist').text(format(report[0][2]));
        $('#eaExpected').text(format(report[0][3]));
        if ($('.fieldworkFreshlist').length > 0)
            $('.fieldworkFreshlist').ClassyLoader({
                percentage: 100. * report[0][1] / report[0][2],
                speed: 20,
                diameter: 70,
                fontSize: '40px',
                roundedLine: true,
                fontColor: '#34788c',
                lineColor: '#34788c',
                remainingLineColor: 'rgba(200,200,200,0.4)',
                lineWidth: 10
            });
        if ($('.fieldworkExpected').length > 0)
            $('.fieldworkExpected').ClassyLoader({
                percentage: 100. * report[0][1] / report[0][3],
                speed: 20,
                diameter: 70,
                fontSize: '40px',
                roundedLine: true,
                fontColor: '#34788c',
                lineColor: '#34788c',
                remainingLineColor: 'rgba(200,200,200,0.4)',
                lineWidth: 10
            });
        if ($('.freshlistExpected').length > 0)
            $('.freshlistExpected').ClassyLoader({
                percentage: 100. * report[0][2] / report[0][3],
                speed: 20,
                diameter: 70,
                fontSize: '40px',
                roundedLine: true,
                fontColor: '#34788c',
                lineColor: '#34788c',
                remainingLineColor: 'rgba(200,200,200,0.4)',
                lineWidth: 10
            });
    });
});