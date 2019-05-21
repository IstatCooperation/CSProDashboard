
$(function () {
    setMenuActive("report-household-" + reportType);
    populate(ctx + "/rest/report/householdBy" + reportType, 'householdlist');
});
