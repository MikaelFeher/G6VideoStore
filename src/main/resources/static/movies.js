console.log("Hello from main.js");

function showMovieDetails(productNumber) {
    window.location.assign(`/movies/movie/${productNumber}/details`);
}

function editMovieDetails(productNumber) {
    window.location.assign(`/movies/movie/${productNumber}/edit`);
}

function deleteMovie(productNumber) {
    window.location.assign(`/movies/movie/${productNumber}/delete`);
}

function showRentalDetails(rentalId) {
    window.location.assign(`/rentedmovies/${rentalId}/rentaldetails`);
}
function showLateRentalDetails(rentalId) {
    window.location.assign(`/rentedmovies/latemovies/${rentalId}/laterentaldetails`);
}
