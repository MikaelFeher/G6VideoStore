function showCustomerDetails(socialSecurityNumber) {
    window.location.assign(`/customer/customer?socialSecurityNumber=${socialSecurityNumber}`);
}