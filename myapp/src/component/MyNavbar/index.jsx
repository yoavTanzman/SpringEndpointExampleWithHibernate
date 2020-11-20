import React, { Component } from 'react';
import {Nav,Navbar} from 'react-bootstrap'
import history from '../../history';
class index extends Component {
    render() {
        return (

            <div>
                
                <Navbar sticky="top" bg="light" expand="lg">
                <Navbar.Brand href="#home">Hibernate-project</Navbar.Brand>
                <Navbar.Toggle aria-controls="basic-navbar-nav" />

<Navbar.Collapse id="responsive-navbar-nav">
            <Nav className="mr-auto">
                    <button style={{marginRight: "5px"}} type="button"
                    className="btn btn-outline-secondary" onClick={() => history.push('/Home')}> CreateNew</button>
                    <button style={{marginRight: "5px"}} className="butonSpace" 
                    type="button" className="btn btn-outline-primary" onClick={() => history.push('/GetCustomer')}>GetCustomer</button>        
                    <button className="butonSpace" 
                    type="button" className="btn btn-outline-info" onClick={() => history.push('/GetAllUsers')}>GetAllUsers</button>  
            </Nav>
</Navbar.Collapse>
</Navbar>




            </div>
        );
    }
}

export default index;