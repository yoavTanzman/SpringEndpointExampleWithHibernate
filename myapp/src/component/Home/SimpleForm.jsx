import React, { Component } from 'react';
import {Form,Button,Col, Row} from 'react-bootstrap'
import './simpleForm.css'
import axios from 'axios'


const createCustomerApi=axios.create({ 
    baseURL:'http://localhost:7074/customer'

})


class SimpleForm extends Component {

    constructor(props) {
        super(props);
        this.state = {

            customerId:'',
            credit:'',
            result:''

        };
    }
    onChange = (e) => {
        /*
          Because we named the inputs to match their
          corresponding values in state, it's
          super easy to update the state
        */
        this.setState({ [e.target.name]: e.target.value });
      }


      handleSubmit = (event) => {
        event.preventDefault();
        let data = {customerId:this.state.customerId,initialCredit:this.state.credit};
        createCustomerApi.post('/add-current-new-customer',data).then(response => {
            console.log(response.status );
            this.setState({ 
                result:response.data

          });
          })
          .catch(error => {
            console.log('health check error'+ error.data);
            this.setState({ 
                result:'user is not exsists: '+error

          });
          });
          this.setState({ 
                customerId:'',
                credit:''

          });
        }

    render() {
        return (
            <div>
                <Form  onSubmit={this.handleSubmit}>
                <Form.Row>  
                    <Form.Group as={Col} md="6" controlId="formBasicEmail">
                        <Form.Label className="formControl">CustomerId</Form.Label>
                        <Form.Control className="formControl"
                            required
                            type="text"
                            placeholder="customerId"
                            name="customerId"
                            value={this.state.customerId}
                            onChange={this.onChange}
                        />
    
                            <Form.Text className="text-muted">
                            please enter your customer Id 
                                </Form.Text>
                            </Form.Group>
                 </Form.Row>
                 <Form.Row>
                 <Form.Group as={Col} md="6" controlId="formBasicEmail">
                        <Form.Label className="formControl">Initial credit</Form.Label>
                        <Form.Control className="formControl"
                            required
                            type="text"
                            placeholder="credit"
                            name="credit"
                            value={this.state.credit}
                            onChange={this.onChange}
                        />
                        
                        <Form.Text className="text-muted">
                            please enter Initial Credit
                                </Form.Text>
                            </Form.Group>
                 </Form.Row>
                            <Button style={{textAlign:"left",float:"left"}} variant="primary" type="submit">
                                Submit
                            </Button>
                </Form>
                <Row style={{marginTop:"90px"}}>
                <div className="resultDiv">
                    {this.state.result}
                </div>
                </Row>

                </div>
        );
    }
}

export default SimpleForm;