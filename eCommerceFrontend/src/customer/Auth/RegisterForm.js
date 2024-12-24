import { Button, Grid, TextField } from "@mui/material";
import React, { useEffect, useState } from "react";
import { useDispatch, useSelector } from "react-redux";
import { useNavigate } from "react-router-dom";
import { getUser, register } from "../../State/Auth/Action";

const RegisterForm = () => {
  const navigate = useNavigate();
  const dispatch = useDispatch();
  const jwt = localStorage.getItem("jwt");
  const { auth } = useSelector((store) => store);
  useEffect(() => {
    if (jwt) {
      dispatch(getUser(jwt));
    }
  }, [jwt, auth.jwt]); // Avoid unnecessary dependencies like auth.jwt

  const [formValues, setFormValues] = useState({
    firstName: "",
    lastName: "",
    email: "",
    password: "",
  });
  const [errors, setErrors] = useState({});

  // Validation function
  const validate = (fieldValues = formValues) => {
    const newErrors = {};

    if ("firstName" in fieldValues && !fieldValues.firstName) {
      newErrors.firstName = "First Name is required";
    }

    if ("lastName" in fieldValues && !fieldValues.lastName) {
      newErrors.lastName = "Last Name is required";
    }

    if ("email" in fieldValues) {
      if (!fieldValues.email) {
        newErrors.email = "Email is required";
      } else if (!/\S+@\S+\.\S+/.test(fieldValues.email)) {
        newErrors.email = "Invalid email address";
      }
    }

    if ("password" in fieldValues) {
      if (!fieldValues.password) {
        newErrors.password = "Password is required";
      } else if (fieldValues.password.length < 6) {
        newErrors.password = "Password must be at least 6 characters";
      }
    }

    return newErrors;
  };

  // Handle input change with real-time validation
  const handleChange = (event) => {
    const { name, value } = event.target;
    setFormValues((prev) => ({ ...prev, [name]: value }));

    // Real-time validation for the specific field
    const fieldError = validate({ [name]: value });
    setErrors((prevErrors) => ({ ...prevErrors, ...fieldError }));
  };

  // Handle form submission
  const handleSubmit = (event) => {
    event.preventDefault();
    const validationErrors = validate();

    if (Object.keys(validationErrors).length > 0) {
      setErrors(validationErrors); // Show all errors
    } else {
      setErrors({});
      console.log("Form Submitted Successfully:", formValues);
      // Proceed with further logic (e.g., API call)
      dispatch(register(formValues));
    }
  };

  return (
    <div>
      <form onSubmit={handleSubmit}>
        <Grid container spacing={3}>
          <Grid item xs={12} sm={6}>
            <TextField
              required
              id="firstName"
              name="firstName"
              label="First Name"
              fullWidth
              autoComplete="given-name"
              value={formValues.firstName}
              onChange={handleChange}
              error={!!errors.firstName}
              helperText={errors.firstName}
            />
          </Grid>
          <Grid item xs={12} sm={6}>
            <TextField
              required
              id="lastName"
              name="lastName"
              label="Last Name"
              fullWidth
              autoComplete="family-name"
              value={formValues.lastName}
              onChange={handleChange}
              error={!!errors.lastName}
              helperText={errors.lastName}
            />
          </Grid>
          <Grid item xs={12}>
            <TextField
              required
              id="email"
              name="email"
              label="Email"
              fullWidth
              autoComplete="email"
              value={formValues.email}
              onChange={handleChange}
              error={!!errors.email}
              helperText={errors.email}
            />
          </Grid>
          <Grid item xs={12}>
            <TextField
              required
              id="password"
              name="password"
              label="Password"
              fullWidth
              autoComplete="new-password"
              type="password"
              value={formValues.password}
              onChange={handleChange}
              error={!!errors.password}
              helperText={errors.password}
            />
          </Grid>
          <Grid item xs={12}>
            <Button
              className="bg-[#9155FD] w-full"
              type="submit"
              variant="contained"
              size="large"
              sx={{
                bgcolor: "#9155FD",
                color: "white",
                padding: "0.8rem 0",
              }}
            >
              Register
            </Button>
          </Grid>
        </Grid>
      </form>
      <div className="flex justify-center flex-col items-center">
        <div className="py-3 flex items-center">
          <p>if you have already account?</p>
          <Button
            onClick={() => navigate("/login")}
            className="ml-5"
            size="small"
          >
            Login
          </Button>
        </div>
      </div>
    </div>
  );
};

export default RegisterForm;
