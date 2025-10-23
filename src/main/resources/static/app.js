$(document).ready(function() {
    function createAddressBook() {
        $.ajax({
            url: '/addressbooks',
            type: 'POST',
            contentType: 'application/json',
            data: JSON.stringify({}),
            success: function(result) {
                console.log("Created:", result);
            },
            error: function(xhr) {
                console.error("Error:", xhr.status, xhr.responseText);
            }
        }).then(function(response) {
        console.log('Address book created with ID: ' + response.id);
        loadAddressBooks();
        });
    }

    function loadAddressBooks() {
        $.ajax({
            url: '/addressbooks',
            type: 'GET',
            contentType: 'application/json',
        }).then(function(addressBooks) {
            const list = $('#addressBookList');
            list.empty();
            addressBooks.forEach(book => {
                const bookDiv = $(`
                <div>
                    <h3>AddressBook #${book.id}</h3>
                    <ul id="buddies-${book.id}">
                        ${book.buddies && book.buddies.length > 0 ?
                    book.buddies.map(b => `<li>${b.name} - ${b.phoneNumber} - ${b.address}</li>`).join('')
                    : '<li><i>No buddies</i></li>'
                }
                    </ul>
                    <input type="text" id="name-${book.id}" placeholder="Name" />
                    <input type="text" id="phone-${book.id}" placeholder="Phone Number" />
                    <input type="text" id="address-${book.id}" placeholder="Address" />
                    <button onclick="addBuddy(${book.id})">Add Buddy</button>
                    <hr/>
                </div>
            `);
                list.append(bookDiv);
                console.log(addressBooks)
            });
        });
    }

    function addBuddy(addressBookId) {
        const name = $(`#name-${addressBookId}`).val();
        const phoneNumber = $(`#phone-${addressBookId}`).val();
        const address= $(`#address-${addressBookId}`).val();

        $.ajax({
            url: `/addressbooks/${addressBookId}/buddies`,
            type: 'POST',
            contentType: 'application/json',
            data: JSON.stringify({ name: name, phoneNumber: phoneNumber, address: address })
        }).then(function(response) {
            loadAddressBooks();
        });
    }


    window.addBuddy = addBuddy;


    $("#createAddressBook").click(createAddressBook);
    loadAddressBooks();


});