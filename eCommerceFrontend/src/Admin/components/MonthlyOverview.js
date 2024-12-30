import React from 'react';
import { 
  AccountCircleOutlined, 
  AttachMoneyOutlined, 
  SettingsCellOutlined, 
  TrendingUp 
} from '@mui/icons-material';
import MoreVertIcon from '@mui/icons-material/MoreVert';
import { Avatar, Box, Card, CardContent, CardHeader, Grid, IconButton, Typography } from '@mui/material';

const salesData = [
  {
    stats: '245K',
    title: "Sales",
    color: "primary.main",
    icon: <TrendingUp sx={{ fontSize: "1.75rem" }} />
  },
  {
    stats: '12.5K',
    title: "Customers",
    color: "success.main",
    icon: <AccountCircleOutlined sx={{ fontSize: "1.75rem" }} />
  },
  {
    stats: '1.54K',
    title: "Products",
    color: "warning.main",
    icon: <SettingsCellOutlined sx={{ fontSize: "1.75rem" }} />
  },
  {
    stats: '88K',
    title: "Revenue",
    color: "info.main",
    icon: <AttachMoneyOutlined sx={{ fontSize: "1.75rem" }} />
  },
];

const renderStats = () => {
  return salesData.map((item, index) => (
    <Grid item xs={12} sm={3} key={index}>
      <Box
        sx={{
          display: "flex",
          alignItems: "center",
        }}
      >
        <Avatar
          variant="rounded"
          sx={{
            mr: 3,
            width: 44,
            height: 44,
            boxShadow: 3,
            color: "white",
            backgroundColor: `${item.color}`, // Ensure correct color is applied
          }}
        >
          {item.icon}
        </Avatar>
        <Box sx={{ display: "flex", flexDirection: "column" }}>
          <Typography variant="caption" sx={{color: "black"}}>
            {item.title}
          </Typography>
          <Typography variant="h6" sx={{color: "black"}}>
            {item.stats}
          </Typography>
        </Box>
      </Box>
    </Grid>
  ));
};

const MonthlyOverview = () => {
  return (
    <Card sx={{}}>
      <CardHeader
        title="Monthly Overview"
        action={
          <IconButton size="small" sx={{ color: "white" }}>
            <MoreVertIcon />
          </IconButton>
        }
        subheader={
          <Typography variant="body2" sx={{ color: "black" }}>
            <Box component="span" sx={{ fontWeight: 600, mx: 2 }}>
              Total 48.5% growth
            </Box>
            😎 this month
          </Typography>
        }
        titleTypographyProps={{
          sx: {
            mb: 2.5,
            lineHeight: "2rem !important",
            letterSpacing: ".15px !important",
          },
        }}
      />
      <CardContent sx={{color: "black", pt: (theme) => `${theme.spacing(3)} !important` }}>
        <Grid container spacing={[5, 0]} >
          {renderStats()} {/* Call the function here */}
        </Grid>
      </CardContent>
    </Card>
  );
};

export default MonthlyOverview;
