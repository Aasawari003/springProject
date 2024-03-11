(function ($) {
  "use strict";

  // Password toggle functionality
  $(".toggle-password").click(function () {
    $(this).toggleClass("fa-eye fa-eye-slash");
    var input = $($(this).attr("toggle"));
    if (input.attr("type") == "password") {
      input.attr("type", "text");
    } else {
      input.attr("type", "password");
    }
  });

  // Confirm password toggle functionality
  $(".toggle-cpassword").click(function () {
    $(this).toggleClass("fa-eye fa-eye-slash");
    var input = $($(this).attr("toggle"));
    if (input.attr("type") == "password") {
      input.attr("type", "text");
    } else {
      input.attr("type", "password");
    }
  });

  // Ensure DOM content is fully loaded before accessing elements
  document.addEventListener("DOMContentLoaded", function () {
    const signupForm = document.getElementById('signup-form');
    const usernameInput = document.getElementById('username');
    const emailInput = document.getElementById('email');
    const passwordInput = document.getElementById('password');
    const confirmPasswordInput = document.getElementById('cpassword');

    signupForm.addEventListener('submit', function (event) {
      event.preventDefault(); // Prevent form submission

      // Validate full name
      if (usernameInput.value.trim() === '') {
        alert('Please enter your full name');
        return;
      }

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

      // Validate password and confirm password match
      if (passwordInput.value !== confirmPasswordInput.value) {
        alert('Password and Confirm Password must match');
        return;
      }

      // Save user data
      saveUserData(usernameInput.value, emailInput.value, passwordInput.value);
    });

    function isValidEmail(email) {
      // Basic email format validation using regex
      const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
      return emailRegex.test(email);
    }

    function saveUserData(username, email, password) {
      const formData = {
        username: username,
        email: email,
        password: password
      };

      $.ajax({
        type: "POST",
        url: "/customersignup",
        data: JSON.stringify(formData),
        contentType: "application/json",
        success: function(response) {
          // Handle successful form submission
          console.log("Form submitted successfully!");
          alert("Form submitted successfully!");
          // Optionally, you can redirect to another page after successful sign-up
        },
        error: function(error) {
          // Handle form submission error
          console.error("Error submitting form:", error);
          alert("An error occurred while submitting the form. Please try again later.");
        }
      });
    }
  });
})(jQuery);
