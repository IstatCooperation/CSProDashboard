var arrLabel = [];
var arrDataMale = [];
var arrDataFemale = [];
var arrColor = [];

$(document).ready(function () {
    $.getJSON(_ctx + "/rest/report/sexByAgeGroup", function (json) {
        $.each(json, function (i, obj) {
            arrLabel.push(obj[1]);
            arrDataMale.push(obj[2]);
            arrDataFemale.push(obj[3]);
        });
        drawBar();
    });
});


function drawBar() {

    var maleSet = {
        x: arrLabel,
        y: arrDataMale,
        name: 'Male',
        type: 'bar',
        hoverinfo: 'skip',
        marker: {
            color: '#2980B9',
            width: 1,
            opacity: 0.9
        }
    };

    var femaleSet = {
        x: arrLabel,
        y: arrDataFemale,
        name: 'Female',
        type: 'bar',
        hoverinfo: 'skip',
        marker: {
            color: '#A93226',
            width: 1,
            opacity: 0.8
        }
    };

    var globalSet = [maleSet, femaleSet];

    var layout = {
        title: 'Sex distribution',
        titlefont: {
            family: 'Courier New, monospace',
            size: 18,
            color: '#5f5f5f'
        },
        showlegend: true,
        xaxis: {
            //tickformat: '%Y/%m/%d',
            title: {
                text: 'Age',
                font: {
                    family: 'Courier New, monospace',
                    size: 18,
                    color: '#5f5f5f'
                }
            }
        },
        yaxis: {
            title: {
                text: 'Total male/female',
                font: {
                    family: 'Courier New, monospace',
                    size: 18,
                    color: '#5f5f5f'
                }
            }
        }
    };

    Plotly.newPlot('chart-area', globalSet, layout, globalOptions);

}

   