package org.example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatusCode;
// Amilesh Nanthakumaran 101231105

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class HttpRequestTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void getAddressBookTest(){
        String url = "http://localhost:"+port+"/addressbooks";
        String response = this.restTemplate.getForEntity(url, String.class).getBody();
        HttpStatusCode code = this.restTemplate.getForEntity(url, String.class).getStatusCode();

        Assertions.assertEquals("[]",response);
        Assertions.assertEquals(HttpStatusCode.valueOf(200), code);
    }
    @Test
    public void addBuddyToAddressBookTest() {
        String url = "http://localhost:"+port+"/addressbooks";
        String response = this.restTemplate.getForEntity(url, String.class).getBody();
        HttpStatusCode code = this.restTemplate.getForEntity(url, String.class).getStatusCode();

        Assertions.assertEquals("[]",response);
        Assertions.assertEquals(HttpStatusCode.valueOf(200), code);

        HttpEntity<AddressBook> request = new HttpEntity<>(new AddressBook());
        AddressBook ad = this.restTemplate.postForObject(url, request, AddressBook.class);
        Assertions.assertNotNull(ad);
        Assertions.assertEquals(0, ad.getBuddies().size());

        HttpEntity<BuddyInfo> request2 = new HttpEntity<>(new BuddyInfo("John", "1234567890", "123 Main St"));
        String resp = this.restTemplate.postForObject(url+"/"+ad.getId()+"/buddies", request2, String.class);


        response = this.restTemplate.getForEntity(url, String.class).getBody();
        System.out.println(response);
        assert(response.contains("John"));

    }
}
