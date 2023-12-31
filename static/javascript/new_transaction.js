const transaction = document.getElementById("transaction");

transaction.addEventListener("submit", (e) => {
    e.preventDefault();

    const data = {
        "transaction_date" : transaction.elements["date"].value,
        "transaction_type" : transaction.elements["type"].value.toLowerCase(),
        "transaction_category" : transaction.elements["category"].value.toLowerCase(),
        "amount" : transaction.elements["amount"].value,
        "title" : transaction.elements["title"].value
    };

    if(transaction.elements["vendor"].value != "") {
        data["vendor"] = transaction.elements["vendor"].value;
    }

    if(transaction.elements["comment"].value != "") {
        data["comment"] = transaction.elements["comment"].value;
    }

    const requestOptions = {
        method: 'POST',
        headers: {
            'Content-Type' : 'application/json'
        },
        body: JSON.stringify(data)
    }

    var account_id = transaction.elements["accounts"].value;
    var url = "http://" + host + ":" + port + "/api/account/" + account_id + "/transaction"

    fetch(url, requestOptions)
        .then(response => {
            if (!response.ok) {
                throw new Error(`HTTP error! Status: ${response.status}`);
            }
            return response.json();
        })
        .then(responseData => {
            document.getElementById('result').textContent = 'Response data: ' + JSON.stringify(responseData);
    })
    .catch(error => {
        console.error('Error:', error);
    });
});