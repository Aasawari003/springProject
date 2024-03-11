(function ($) {
  "use strict";

  var fullHeight = function () {
    $('.js-fullheight').css('height', $(window).height());
    $(window).resize(function () {
      $('.js-fullheight').css('height', $(window).height());
    });
  };
  fullHeight();

  $(".toggle-password").click(function () {
    $(this).toggleClass("fa-eye fa-eye-slash");
    var input = $($(this).attr("toggle"));
    if (input.attr("type") == "password") {
      input.attr("type", "text");
    } else {
      input.attr("type", "password");
    }
  });

})(jQuery);

document.addEventListener("DOMContentLoaded", function () {
    const form = document.querySelector('.signin-form');
    const emailInput = document.getElementById('username');
    const passwordInput = document.getElementById('password');
    const rememberMeCheckbox = document.getElementById('remember-me');

    form.addEventListener('submit', function (event) {
        event.preventDefault(); // Prevent form submission

        // Validate email format
        if (!isValidEmail(emailInput.value)) {
            alert('Please enter a valid email address');
            return;
        }

        // Validate password length
        if (passwordInput.value.length < 6) {
            alert('Password must be at least 6 characters long');
            return;
        }

        // Construct the data to be sent to the server
        const requestData = {
            email: emailInput.value,
            password: passwordInput.value
        };

        // Make AJAX request to sign in endpoint
        $.ajax({
            url: '/customersignin',
            method: 'POST',
            contentType: 'application/json',
            data: JSON.stringify(requestData),
            success: function (response) {
                // Handle successful sign-in
                alert('Login Successful');
                // Optionally, you can redirect the user to another page or perform other actions
            },
            error: function (xhr, status, error) {
                // Handle sign-in error
                console.error('Error signing in:', error);
                alert('An error occurred while signing in. Please try again.');
            }
        });

        // Proceed with form submission or any other action
        // Example: form.submit();
    });

    function isValidEmail(email) {
        // Basic email format validation using regex
        const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
        return emailRegex.test(email);
    }

    function saveCredentials(email, password) {
        // Save credentials to localStorage
        localStorage.setItem('email', email);
        localStorage.setItem('password', password);

        // Log saved credentials to the console
        console.log('Credentials saved:');
        console.log('Email:', email);
        console.log('Password:', password);
    }
});
