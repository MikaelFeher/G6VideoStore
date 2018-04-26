console.log("Hello from main.js");

/* Movies */
function showMovieDetails(productNumber) {
    window.location.assign(`/movies/movie/${productNumber}/details`);
}

function editMovieDetails(productNumber) {
    window.location.assign(`/movies/movie/${productNumber}/edit`);
}

function deleteMovie(productNumber) {
    window.location.assign(`/movies/movie/${productNumber}/delete`);
}

/* Rentals */
function showRentalDetails(rentalId) {
    window.location.assign(`/rentedmovies/${rentalId}/rentaldetails`);
}
function showLateRentalDetails(rentalId) {
    window.location.assign(`/rentedmovies/latemovies/${rentalId}/laterentaldetails`);
}

/* Customers */
function showCustomerDetails(socialSecurityNumber) {
    window.location.assign(`/customer/customer?socialSecurityNumber=${socialSecurityNumber}`);
}