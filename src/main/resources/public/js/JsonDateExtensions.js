
if (this.JSON && !this.JSON.dateParser) {

    JSON.useDateParser = function(reset) {
        var reISO = /^(\d{4})-(\d{2})-(\d{2})T(\d{2}):(\d{2}):(\d{2}(?:\.\d*))(?:Z|(\+|-)([\d|:]*))?$/;
        if (reset) {
            if (JSON._parseSaved) {
                JSON.parse = JSON._parseSaved;
                JSON._parseSaved = null;
                JSON.stringify = JSON._stringifySaved;
                JSON._stringifySaved = null;
            }
        } else {
            if (!JSON.parseSaved) {
                JSON._parseSaved = JSON.parse;
                JSON.parse = function (o) {
                    return JSON._parseSaved(o,function (key,value) {
                        if (typeof value === 'string') {
                            if (reISO.test(value)) {
                                var d = new Date(value);
                                console.log(d);
                                return d;
                            }
                        }
                        return value;
                    });
                };
            }
        }
    };

    JSON.useDateParser();
}
