import React, { Component } from 'react';
import { Link, withRouter } from 'react-router-dom';
import { Button, Container, Form, FormGroup, Input, Label } from 'reactstrap';
import AppNavbar from './AppNavbar';

class ContactoEdit extends Component {

    emptyItem = {
        id: '',
        id_usu1: '',
        id_usu2: '',
        duracion: '',
        caducidad: ''
    };

    constructor(props) {
        super(props);
        this.state = {
            item: this.emptyItem
        };
        this.handleChange = this.handleChange.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this);
    }

    async componentDidMount() {
        if (this.props.match.params.id !== 'new') { // 
            const contacto_estrecho = await (await fetch(`/contactos/${this.props.match.params.id}`)).json();
            this.setState({item: contacto_estrecho});
        }
    }

    handleChange(event) {
        const target = event.target;
        const value = target.value;
        const name = target.name;
        let item = {...this.state.item};
        item[name] = value;
        this.setState({item});
    }

async handleSubmit(event) {
    event.preventDefault();
    const {item} = this.state;

    await fetch('/contactos' + ((this.props.match.params.id !== 'new') ? '/' + item.id : ''), {
        method: (this.props.match.params.id !== 'new') ? 'PUT' : 'POST', //(item.email) ? 'PUT' : 'POST',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(item),
    });
    this.props.history.push('/contactos');
}

    render() {
        const {item} = this.state;
        const title = <h2>{item.id ? 'Edit contacto' : 'Add contact'}</h2>;

        return <div>
            <AppNavbar/>
            <Container>
                {title}
                <Form onSubmit={this.handleSubmit}>
                    <FormGroup>
                        <Label for="id1">Id</Label>
                        <Input type="text" name="id1" id="id1" value={item.id || ''}
                               onChange={this.handleChange} autoComplete="id1"/>
                    </FormGroup>
                    <FormGroup>
                        <Label for="id_usu1">Id Usuario 1</Label>
                        <Input type="text" name="id_usu1" id="id_usu1" value={item.id_usu1 || ''}
                               onChange={this.handleChange} autoComplete="id_usu1"/>
                    </FormGroup>
                    <FormGroup>
                        <Label for="id_usu2">Id Usuario 2</Label>
                        <Input type="text" name="id_usu2" id="id_usu2" value={item.id_usu2 || ''}
                               onChange={this.handleChange} autoComplete="id_usu2"/>
                    </FormGroup>
                    <FormGroup>
                        <Label for="duracion">Duración en Contacto</Label>
                        <Input type="text" name="duracion" id="duracion" value={item.duracion || ''}
                               onChange={this.handleChange} autoComplete="duracion"/>
                    </FormGroup>
                    <FormGroup>
                        <Label for="caducidad">Caducidad del Contacto</Label>
                        <Input type="text" name="caducidad" id="caducidad" value={item.caducidad || ''}
                               onChange={this.handleChange} autoComplete="caducidad"/>
                    </FormGroup>

                    <FormGroup>
                        <Button color="primary" type="submit">Save</Button>{' '}
                        <Button color="secondary" tag={Link} to="/contactos">Cancel</Button>
                    </FormGroup>
                </Form>
            </Container>
        </div>
    }
}

export default withRouter(ContactoEdit);