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


