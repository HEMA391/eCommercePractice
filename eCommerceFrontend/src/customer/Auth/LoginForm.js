import { Button, Grid, TextField } from "@mui/material";
import React, { useState } from "react";
import { useDispatch } from "react-redux";
import { useNavigate } from "react-router-dom";
import { login } from "../../State/Auth/Action";

const LoginForm = () => {
  const dispatch = useDispatch();
  const navigate = useNavigate();

  const [formValues, setFormValues] = useState({
    email: "",
    password: "",
  });
  const [errors, setErrors] = useState({});

  // Validation function
  const validate = (fieldValues = formValues) => {
    const newErrors = {};

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
      dispatch(login(formValues));
      console.log("Form Submitted Successfully:", formValues);
      // Proceed with further logic (e.g., API call)
    }
  };

  return (
    <div>
      <form onSubmit={handleSubmit}>
        <Grid container spacing={3}>
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
              sx={{ bgcolor: "#9155FD", color: "white", padding: "0.8rem 0" }}
            >
              Login
            </Button>
          </Grid>
        </Grid>
      </form>
      <div className="flex justify-center flex-col items-center">
        <div className="py-3 flex items-center">
          <p>if you don't have an account?</p>
          <Button
            onClick={() => navigate("/register")}
            className="ml-5"
            size="small"
          >
            Register
          </Button>
        </div>
      </div>
    </div>
  );
};

export default LoginForm;
