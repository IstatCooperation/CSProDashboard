$(function () {

    setMenuActive("map");

    var map = L.map('map-container', {
        center: [7.4608454, -7.7917077],
        zoom: 8
    });

    var resize = function () {
        var $map = $('#map-container');
        $map.height($(window).height() - 300);
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

    setCountry(map);
    setRegion(map);
    //setMarkers(map);

});

function reset() {
    window.location = ctx + "/gis/map";
}

function setCountry(map) {

    var countryStyle = {
        color: "#ff7800",
        weight: 2,
        opacity: 0.65
    };

    $.ajax({
        dataType: "json",
        url: 'https://nominatim.openstreetmap.org/search?country=cote%20d%27ivoire&polygon_geojson=1&format=geojson',
        success: function (data) {
            var countryLayer = L.geoJSON(data, {
                style: countryStyle
            });
            countryLayer.addTo(map);
            map.fitBounds(countryLayer.getBounds());
        }
    });

}

function setRegion(map) {

    var regionStyle = {
        color: "#ff7800",
        weight: 0,
        opacity: 0.5
    };

    $.ajax({
        dataType: "json",
        url: 'https://nominatim.openstreetmap.org/search?q=montagnes,cote%20d%27ivoire&polygon_geojson=1&format=geojson',
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
