$(function () {

    var map = L.map('mapid', {
        center: [7.4608454, -7.7917077],
        zoom: 8
    });

    var resize = function () {
        var $map = $('#map');
        $map.height($(window).height());
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
    
    var regionStyle = {
        color: "#e70000",
        weight: 0
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
    
    L.marker([7.514788400, -7.94027340]).addTo(map).openPopup();
});

function reset(){
    window.location = ctx + "/gis/map";
}



