import React, { useState } from 'react';
import { Box, CssBaseline, Drawer, List, ListItem, ListItemButton, ListItemIcon, ListItemText, Toolbar, useMediaQuery, useTheme } from '@mui/material';
import { Route, Routes, useNavigate } from 'react-router-dom';
import DashboardIcon from '@mui/icons-material/Dashboard';
import AccountCircleIcon from '@mui/icons-material/AccountCircle';
import CreateProductForm from './CreateProductForm';
import ProductsTable from './ProductsTable';
import OrdersTable from './OrdersTable';
import CustomersTable from './CustomersTable';
import AdminDashBoard from './AdminDashBoard';

const menu = [
    { name: 'Dashboard', path: '/admin', icon: <DashboardIcon /> },
    { name: 'Products', path: '/admin/products', icon: <DashboardIcon /> },
    { name: 'Customers', path: '/admin/customers', icon: <DashboardIcon /> },
    { name: 'Orders', path: '/admin/orders', icon: <DashboardIcon /> },
    { name: 'AddProduct', path: '/admin/product/create', icon: <DashboardIcon /> },
  ];
  

const Admin = () => {
  const theme = useTheme();
  const isLargeScreen = useMediaQuery(theme.breakpoints.up('lg'));
  const [sidebarVisible, setSidebarVisible] = useState(false);
  const navigate = useNavigate();

  const drawer = (
    <Box sx={{
        overflow: 'auto',
        height: '100%',
        display: 'flex',
        flexDirection: 'column',
        justifyContent: 'space-between',
      }}>
      {/* {isLargeScreen && <Toolbar/>} */}
      <List>
        {menu.map((item, index) => (
          <ListItem key={item.name} disablePadding onClick={() => navigate(item.path)}>
            <ListItemButton>
              <ListItemIcon>{item.icon}</ListItemIcon>
              <ListItemText>{item.name}</ListItemText>
            </ListItemButton>
          </ListItem>
        ))}
      </List>
      <List>
        <ListItem disablePadding>
          <ListItemButton>
            <ListItemIcon>
              <AccountCircleIcon />
            </ListItemIcon>
            <ListItemText>Account</ListItemText>
          </ListItemButton>
        </ListItem>
      </List>
    </Box>
  );

  return (
    <div className="relative flex h-[100vh]">
      {/* <CssBaseline /> */}
      <div className='shadow-lg shadow-gray-600 w-[15%] h-full fixed top-0'>
        {drawer}
        </div>

      <div className="w-[85%] h-full ml-[15%]">
        <Routes>
          <Route path="/" element={<AdminDashBoard />} />
          <Route path="/admin" element={<AdminDashBoard />} />
          <Route path="/product/create" element={<CreateProductForm />} />
          <Route path="/products" element={<ProductsTable />} />
          <Route path="/orders" element={<OrdersTable />} />
          <Route path="/customers" element={<CustomersTable />} />
          {/* <Route index element={<AdminDashBoard />} /> */}
          
        </Routes>
      </div>
    </div>
  );
};

export default Admin;
