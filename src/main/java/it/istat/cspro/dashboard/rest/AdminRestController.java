package it.istat.cspro.dashboard.rest;

import it.istat.cspro.dashboard.forms.ReportUpdateForm;
import it.istat.cspro.dashboard.service.DashboardService;
import it.istat.cspro.dashboard.service.NotificationService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AdminRestController {

    @Autowired
    private DashboardService dashboardService;
    @Autowired
    private NotificationService notificationService;

    @RequestMapping(value = "/rest/admin/list/{key}")
    public List listReport(@PathVariable("key") String key) {
        switch (key) {
            case "errors":
                return dashboardService.getErrors();
        }
        return new ArrayList(0);
    }

    @RequestMapping(value = "/rest/admin/{key}")
    public Object objectReport(@PathVariable("key") String key) {
        switch (key) {
        }
        return new ArrayList(0);
    }

    @RequestMapping(value = "/rest/admin/editReport", method = RequestMethod.POST)
    public List<NotificationService.NotificationMessage> updateReport(ReportUpdateForm form, BindingResult bindingResult) {
        notificationService.removeAllMessages();
        if (!bindingResult.hasErrors()) {
            try {
                dashboardService.updateReport(form);
                notificationService.addInfoMessage("Report updated");
            } catch (Exception e) {
                notificationService.addErrorMessage("Error: " + e.getMessage());
            }
        } else {
            List<FieldError> errors = bindingResult.getFieldErrors();
            for (FieldError error : errors) {
                notificationService.addErrorMessage(error.getField() + " - " + error.getDefaultMessage());
            }
        }
        return notificationService.getNotificationMessages();
    }

}
