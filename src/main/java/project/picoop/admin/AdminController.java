package project.picoop.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import project.picoop.admin.data.WebData;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    /**
     * It calls AdminService getWebData method
     * 
     * @return {@link WebData}
     */
    @GetMapping("/data")
    public WebData getWebData() {
        return adminService.getWebData();
    }

}
