const initialMovies = [
    { id: 1, title: "Inception", genre: "Sci-Fi", rate: "₱30/day", status: "Available", renter: "-" },
    { id: 2, title: "The Dark Knight", genre: "Action", rate: "₱45/day", status: "Rented", renter: "John Doe" },
    { id: 3, title: "Pulp Fiction", genre: "Crime", rate: "₱20/day", status: "Available", renter: "-" },
    { id: 4, title: "Interstellar", genre: "Sci-Fi", rate: "₱35/day", status: "Rented", renter: "Jane Doe" },
    { id: 5, title: "Spirited Away", genre: "Animation", rate: "₱25/day", status: "Available", renter: "-" }
];

document.addEventListener('DOMContentLoaded', () => {

    if (localStorage.getItem('isLoggedIn') !== 'true') {
        window.location.href = 'index.html';
        return;
    }

    const currentUser = localStorage.getItem('currentUser') || 'Admin';
    document.getElementById('usernameDisplay').textContent = currentUser;

    let movies = initialMovies;
    if (!localStorage.getItem('videoCatalog')) {
        localStorage.setItem('videoCatalog', JSON.stringify(movies));
    }

    const tableBody = document.getElementById('movieTableBody');
    const searchInput = document.getElementById('searchInput');

    function render() {
        renderStats(movies);
        renderTable(movies);
    }

    function renderStats(movieList) {
        const total = movieList.length;
        const available = movieList.filter(m => m.status === 'Available').length;
        const rented = movieList.filter(m => m.status === 'Rented').length;

        document.getElementById('totalMovies').textContent = total;
        document.getElementById('availableMovies').textContent = available;
        document.getElementById('rentedMovies').textContent = rented;
    }

    function renderTable(movieList) {
        tableBody.innerHTML = '';

        if (movieList.length === 0) {
            tableBody.innerHTML = `<tr><td colspan="6" style="text-align:center;">No movies found.</td></tr>`;
            return;
        }

        movieList.forEach(movie => {
            const row = document.createElement('tr');

            const isAvailable = movie.status === 'Available';
            const badgeClass = isAvailable ? 'available' : 'rented';
            const buttonText = isAvailable ? 'Rent Out' : 'Return Movie';
            const buttonClass = isAvailable ? 'btn-rent' : 'btn-return';

            row.innerHTML = `
                <td><strong>${movie.title}</strong></td>
                <td>${movie.genre}</td>
                <td>${movie.rate}</td>
                <td><span class="badge ${badgeClass}">${movie.status}</span></td>
                <td>${movie.renter}</td>
                <td>
                    <button class="btn-action ${buttonClass}" onclick="toggleRental(${movie.id})">
                        ${buttonText}
                    </button>
                </td>
            `;
            tableBody.appendChild(row);
        });
    }

    window.toggleRental = function(id) {
        movies = movies.map(movie => {
            if (movie.id === id) {
                if (movie.status === 'Available') {
                    const renterName = prompt(`Enter customer name for "${movie.title}":`);
                    if (renterName && renterName.trim() !== '') {
                        movie.status = 'Rented';
                        movie.renter = renterName.trim();
                    }
                } else {
                    movie.status = 'Available';
                    movie.renter = '-';
                }
            }
            return movie;
        });

        localStorage.setItem('videoCatalog', JSON.stringify(movies));
        render();
    };

    searchInput.addEventListener('input', (e) => {
        const query = e.target.value.toLowerCase();
        const filteredMovies = movies.filter(movie => 
            movie.title.toLowerCase().includes(query) || 
            movie.genre.toLowerCase().includes(query)
        );
        renderTable(filteredMovies);
    });

    document.getElementById('logoutBtn').addEventListener('click', () => {
        localStorage.removeItem('isLoggedIn');
        localStorage.removeItem('currentUser');
        window.location.href = '../index.html';
    });

    render();
});