document.addEventListener('DOMContentLoaded', () => {
    const loginForm = document.getElementById('loginForm');
    const errorMessage = document.getElementById('errorMessage');

    if (localStorage.getItem('isLoggedIn') === 'true') {
        window.location.href = 'pages/dashboard.html';
    }

    loginForm.addEventListener('submit', (e) => {
        e.preventDefault();
        
        const usernameInput = document.getElementById('username').value.trim();
        const passwordInput = document.getElementById('password').value.trim();

        if (passwordInput === '1234') {
            localStorage.setItem('isLoggedIn', 'true');
            localStorage.setItem('currentUser', usernameInput);
            window.location.href = 'pages/dashboard.html';
        } else {
            errorMessage.textContent = 'Invalid username or password.';
        }
    });
});