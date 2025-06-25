import React from 'react';
import { useState, useEffect } from "react";
import { Link, useNavigate } from "react-router-dom";
import '../App.css';
import userPic from'../Images/user.png'; 
import { useParams } from "react-router-dom";
import { MDBCol, MDBContainer, MDBRow, MDBCard, MDBCardText, MDBCardBody, MDBCardImage, MDBTypography, MDBIcon } from 'mdb-react-ui-kit';
import UpdateUser from './UpdateUser';


export default function PersonalProfile() {
  const [items, setItems] = useState(null);
  const {username} = useParams();
  const navigate = useNavigate();

  //DB connection
  useEffect(() => {
          const fetchData = async () => {
              try {
                  const usernameExists = String(username);
                  const response = await fetch(`http://localhost:8080/admin/getUserByUsername/?username=${encodeURIComponent(usernameExists)}`);
                  const result = await response.json();
                  setItems(result);
              } catch (error) {
                  console.error("Error fetching event:", error);
              }
          };
          fetchData();
      }, [username]);  

  if (!items) return <p>User not found</p>;
 
   const goToUpdate = () => {
    navigate("/UpdateUser", { state: { items } });
  };
      //User Profile
  return (
    <section className="vh-100" style={{ backgroundColor: '#f4f5f7' }}>
      <MDBContainer className="py-5 h-100"> 
        <MDBRow className="justify-content-center align-items-center h-100">
          <MDBCol lg="6" className="mb-4 mb-lg-0">
            <MDBCard className="mb-3" style={{ borderRadius: '.5rem' }}>
              <MDBRow className="g-0">
                <MDBCol md="4" className="gradient-custom text-center text-white"
                  style={{ borderTopLeftRadius: '.5rem', borderBottomLeftRadius: '.5rem', backgroundColor:'#34a3da' }}>
                  <MDBCardImage src= {userPic} alt="Avatar" className="my-5" style={{ width: '80px' }} fluid />
                  <MDBTypography tag="h5">{items.username}</MDBTypography>
                  <br></br>
                  <button onClick={goToUpdate} style={{backgroundColor:'white', borderColor:'white', color:'#34a3da', fontStyle:'bold'}} >Edit Profile </button>
                </MDBCol>
                <MDBCol md="8">
                  <MDBCardBody className="p-4">
                    <MDBTypography tag="h6">Information</MDBTypography>
                    <hr className="mt-0 mb-4" />
                    <MDBRow className="pt-1">
                      <MDBCol size="6" className="mb-3">
                        <MDBTypography tag="h6">Name</MDBTypography>
                        <MDBCardText className="text-muted">{items.name}</MDBCardText>
                        <MDBTypography tag="h6">Surname</MDBTypography>
                        <MDBCardText className="text-muted">{items.surname}</MDBCardText>
                        <MDBTypography tag="h6">Email</MDBTypography>
                        <MDBCardText className="text-muted" style={{width:'1000px'}}>{items.email}</MDBCardText>
                      </MDBCol>
                    </MDBRow>
                  </MDBCardBody>
                </MDBCol>
              </MDBRow>
            </MDBCard>
          </MDBCol>
        </MDBRow>
      </MDBContainer>
    </section>
  );
}