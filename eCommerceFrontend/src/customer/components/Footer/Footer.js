import { Button, Grid2, Link, Typography } from "@mui/material";
import React from "react";

const Footer = () => {
  return (
    <div>
      {/* Main Content Grid */}
      <Grid2
        container
        justifyContent="space-around" // Distribute items with equal spacing
        alignItems="flex-start" // Align items at the top
        spacing={5} // Adds space between grid items
        sx={{ bgcolor: "black", color: "white", py: 3, textAlign: "center" }} // sx-styles in MUI
      >
        <Grid2 item xs={12} sm={6} md={3}>
          <Typography variant="h6" sx={{ pb: 2 }}>
            Company
          </Typography>
          {["About", "Blog", "Press", "Jobs", "Partners"].map((text) => (
            <Button
              key={text}
              sx={{
                display: "block",
                mb: 1,
                color: "white",
                textTransform: "none",
              }}
            >
              {text}
            </Button>
          ))}
        </Grid2>

        <Grid2 item xs={12} sm={6} md={3}>
          <Typography variant="h6" sx={{ pb: 2 }}>
            Solutions
          </Typography>
          {["Marketing", "Analytics", "Commerce", "Insights", "Support"].map(
            (text) => (
              <Button
                key={text}
                sx={{
                  display: "block",
                  mb: 1,
                  color: "white",
                  textTransform: "none",
                }}
              >
                {text}
              </Button>
            )
          )}
        </Grid2>

        <Grid2 item xs={12} sm={6} md={3}>
          <Typography variant="h6" sx={{ pb: 2 }}>
            Documentation
          </Typography>
          {["Guides", "API Status"].map((text) => (
            <Button
              key={text}
              sx={{
                display: "block",
                mb: 1,
                color: "white",
                textTransform: "none",
              }}
            >
              {text}
            </Button>
          ))}
        </Grid2>

        <Grid2 item xs={12} sm={6} md={3}>
          <Typography variant="h6" sx={{ pb: 2 }}>
            Legal
          </Typography>
          {["Claim", "Privacy", "Terms"].map((text) => (
            <Button
              key={text}
              sx={{
                display: "block",
                mb: 1,
                color: "white",
                textTransform: "none",
              }}
            >
              {text}
            </Button>
          ))}
        </Grid2>
      </Grid2>

      {/* Copyright Section */}
      <Grid2
        container
        justifyContent="center" // Center content horizontally
        sx={{ bgcolor: "black", color: "white", py: 2, textAlign: "center" }}
      >
        <Grid2 item xs={12}>
          <Typography variant="body2" component="p" align="center">
            &copy; 2023 My Company. All rights reserved.
          </Typography>
          <Typography variant="body2" component="p" align="center">
            Made with React and Spring Boot
          </Typography>
          <Typography variant="body2" component="p" align="center">
            Icons made by{" "}
            <Link
              href="https://www.freepik.com"
              color="inherit"
              underline="always"
            >
              Freepik
            </Link>{" "}
            from{" "}
            <Link
              href="https://www.flaticon.com/"
              color="inherit"
              underline="always"
            >
              www.flaticon.com
            </Link>
          </Typography>
        </Grid2>
      </Grid2>
    </div>
  );
};

export default Footer;
