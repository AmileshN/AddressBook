package org.example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class HttpRequestTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void getAddressBook() {
        String url = "http://localhost:"+port+"/addressbooks";
        String response = this.restTemplate.getForEntity(url, String.class).getBody();
        assert(response.contains("[]"));

        HttpEntity<AddressBook> request = new HttpEntity<>(new AddressBook());
        AddressBook ad = this.restTemplate.postForObject(url, request, AddressBook.class);
        Assertions.assertNotNull(ad);
        Assertions.assertEquals(0, ad.getBuddies().size());

        HttpEntity<BuddyInfo> request2 = new HttpEntity<>(new BuddyInfo("John", "1234567890"));
        this.restTemplate.postForObject(url+"/"+ad.getId()+"/buddies", request2, BuddyInfo.class);

        url = "http://localhost:"+port+"/addressbooks";
        response = this.restTemplate.getForEntity(url, String.class).getBody();
        System.out.println(response);
        assert(response.contains("John"));

    }
}
