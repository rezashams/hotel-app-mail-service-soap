package com.hotel.mail.comhotelmail.endpoints;

import com.hotel.mail.comhotelmail.service.MailService;
import com.hotelapp.xml.mail.SendEmailRequest;
import com.hotelapp.xml.mail.SendEmailResponse;
import com.hotelapp.xml.mail.ServiceStatus;
import com.mailjet.client.errors.MailjetException;
import com.mailjet.client.errors.MailjetSocketTimeoutException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
public class MailEndPoints {

    private final MailService mailService;
    private static final String NAMESPACE_URI = "http://www.hotelapp.com/xml/mail";

    @Autowired
    public MailEndPoints(MailService mailService) {
        this.mailService = mailService;
    }


    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "sendEmailRequest")
    @ResponsePayload
    public SendEmailResponse  sendMail(@RequestPayload SendEmailRequest request) {
        System.out.println("Start sending mail");
        ServiceStatus serviceStatus = new ServiceStatus();

        try {
            mailService.sendEmail(request.getRoomName(), request.getPayablePrice(), request.getDiscountPrice(),
                    request.getEmail());
            serviceStatus.setStatusCode("SUCCESS");
            serviceStatus.setMessage("Mail Deleted Successfully");
        } catch (MailjetSocketTimeoutException | MailjetException e) {
            e.printStackTrace();
            serviceStatus.setStatusCode("FAIL");
            serviceStatus.setMessage("Mail Not Available");
        }
        SendEmailResponse response = new SendEmailResponse();
        response.setServiceStatus(serviceStatus);
        return response;
    }
}
