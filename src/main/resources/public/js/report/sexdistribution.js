var _ctx = $("meta[name='ctx']").attr("content");

var removebtns = ['lasso2d', 'hoverClosestCartesian', 'hoverCompareCartesian', 'toggleSpikelines', 'select2d', 'pan2d', 'zoom2d', 'zoomIn2d', 'zoomOut2d'];
var globalOptions = {scrollZoom: true, staticPlot: false, displaylogo: false, responsive: true, modeBarButtonsToRemove: removebtns};

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

    var maleBox = {
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

    var femaleBox = {
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

    var globalBox = [maleBox, femaleBox];

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

    Plotly.newPlot('chart-area', globalBox, layout, globalOptions);

}

   