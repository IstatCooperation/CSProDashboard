var country = "Kenya";
var states = [];

//colors
var RED = '#cc3232';
var ORANGE = '#db7b2b';
var YELLOW = '#e7b416';
var GREEN = '#2dc937';

$(function () {

    var map = L.map('map-container', {
        center: [-0.022, 37.749],
        zoom: 5
    });

    var resize = function () {
        var $map = $('#map-container');
        $map.height($(window).height() - 240);
        if (map) {
            map.invalidateSize();
        }
    };

    $(window).on('resize', function () {
        resize();
    });

    resize();

    var baseLayer = L.tileLayer('https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {
        attribution: '&copy; <a href="https://www.openstreetmap.org/copyright">OpenStreetMap</a> contributors'
    });

    baseLayer.addTo(map);

    //Create a legend
    var legend = L.control({position: 'bottomright'});

    legend.onAdd = function (map) {
        var div = L.DomUtil.create('div', 'info legend');
        var grades = [0, 25, 50, 75];

        for (var i = 0; i < grades.length; i++) {
            div.innerHTML += '<i style="background:' + getColor(grades[i] + 1) + '"></i>';
            if(i === grades.length - 1){
                div.innerHTML += ' &gt;' + grades[i] + '%<br>';
            } else{
                div.innerHTML += grades[i] + (grades[i + 1] ? ' &ndash; ' + grades[i + 1] + '%<br>' : '+');
            }
        }
        return div;
    };

    legend.addTo(map);

    if (country !== "") {
        setCountry(map);
    }

    getStateCoverage(); //get states
    if (states.length !== 0) {
        var stateName = "";
        var stateCoverage = 0.0;
        for (var i = 0; i < states.length; i++) {
            stateName = states[i][0];
            stateCoverage = states[i][1];
            //PATCH FOR KENYA
            if (stateName === 'TAITATAVETA') {
                stateName = 'TAITA TAVETA'
            } else if (stateName === 'ELGEYO MARAKWET') {
                stateName = 'ELEGEYO-MARAKWET'
            }
            setStateByCoverage(stateName, stateCoverage, map);
        }

    }

    //setMarkers(map);

});

function reset(page) {
    window.location = ctx + page;
}

function setCountry(map) {

    var countryStyle = {
        color: "#ff7800",
        weight: 2,
        opacity: 0.01
    };

    $.ajax({
        dataType: "json",
        url: 'https://nominatim.openstreetmap.org/search?country=' + country + '&polygon_geojson=1&format=geojson',
        success: function (data) {
            var countryLayer = L.geoJSON(data, {
                style: countryStyle
            });
            countryLayer.addTo(map);
            map.fitBounds(countryLayer.getBounds());
        }
    });

}


function getStateCoverage() {
    $.ajax({
        url: ctx + "/rest/territory/coverage/root",
        type: "GET",
        async: false, //must wait until it completes
        success: function (data) {
            states = data;
        },
        error: function () {
            alert('Error loading data');
        }
    });
}

function setStateByCoverage(stateName, stateCoverage, map) {

    var stateStyle = {
        color: getColor(stateCoverage),
        weight: 1,
        opacity: 0.9,
        fillOpacity: 0.7
    };

    $.ajax({
        dataType: "json",
        url: 'https://nominatim.openstreetmap.org/search?state=' + stateName + ',&coutry=' + country + '&polygon_geojson=1&format=geojson&limit=1',
        success: function (data) {
            if (typeof data.features[0] !== 'undefined' && data.features[0].geometry.type !== 'Point') {
                var regionLayer = L.geoJSON(data, {
                    style: stateStyle
                });
                regionLayer.addTo(map);
            } else {
                console.log(stateName);
            }
        }
    });
}


function setRegion(map) {

    var regionStyle = {
        color: "#cc3232",
        weight: 1
    };

    $.ajax({
        dataType: "json",
        url: 'https://nominatim.openstreetmap.org/search?q=' + region + ',' + country + '&polygon_geojson=1&format=geojson',
        success: function (data) {
            var regionLayer = L.geoJSON(data, {
                style: regionStyle
            });
            regionLayer.addTo(map);
        }
    });
}

function setMarkers(map) {

    var markerStyle = {
        color: "#196889",
        weight: 0,
        opacity: 0.5
    };

    $.ajax({
        dataType: "json",
        url: ctx + '/rest/territory/coordinates',
        success: function (data) {
            var markers = L.markerClusterGroup();
            //markers.addLayer(L.marker(getRandomLatLng(map)));
            $.each(data, function (i) {
                var marker = L.marker([data[i].lat, data[i].lon]);
                markers.addLayer(marker);
            });
            map.addLayer(markers);
        }
    });
}

function getColor(coverage) {
    if (coverage >= 0 && coverage < 25) {
        return RED;
    } else if (coverage >= 25 && coverage < 50) {
        return ORANGE;
    } else if (coverage >= 50 && coverage < 75) {
        return YELLOW;
    } else if (coverage >= 75) {
        return GREEN;
    }
}