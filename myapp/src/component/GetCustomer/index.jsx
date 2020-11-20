import React, { Component } from 'react';
import { Container } from 'react-bootstrap';
import MyNavbar from '../MyNavbar'
import SimpleFormCustomer from './SimpleFormCustomer'


class index extends Component {
    render() {
        return (
            <Container>
                <MyNavbar/>
                <SimpleFormCustomer/>
            </Container>
        );
    }
}


export default index;