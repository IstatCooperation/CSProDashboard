$(function () {
    $.getJSON(ctx + "/rest/report/total", function (report) {
        $('#householdFieldwork').text(format(report[0][4]));
        $('#householdFreshlist').text(format(report[0][5]));
        $('#householdExpected').text(format(report[0][6]));
        if ($('.fieldworkFreshlist').length > 0)
            $('.fieldworkFreshlist').ClassyLoader({
                percentage: 100. * report[0][4] / report[0][5],
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
                percentage: 100. * report[0][4] / report[0][6],
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
                percentage: 100. * report[0][5] / report[0][6],
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

