import React, { Component } from 'react';
import { Link, withRouter } from 'react-router-dom';
import { Button, Container, Form, FormGroup, Input, Label } from 'reactstrap';
import AppNavbar from './AppNavbar';

class TFGEdit extends Component {

    emptyItem = {
        id: '',
        email: '',
        contraseña: '',
        positivo: false,
        contactos: []
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
            const usuario = await (await fetch(`/usuarios/${this.props.match.params.id}`)).json();
            this.setState({item: usuario});
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

    await fetch('/usuarios' + ((this.props.match.params.id !== 'new') ? '/' + item.id : ''), {
        method: (this.props.match.params.id !== 'new') ? 'PUT' : 'POST', //(item.email) ? 'PUT' : 'POST',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(item),
    });
    this.props.history.push('/usuarios');
}

    render() {
        const {item} = this.state;
        const title = <h2>{item.id ? 'Edit user' : 'Add user'}</h2>;

        return <div>
            <AppNavbar/>
            <Container>
                {title}
                <Form onSubmit={this.handleSubmit}>
                    <FormGroup>
                        <Label for="id">Id</Label>
                        <Input type="text" name="id" id="id" value={item.id || ''}
                               onChange={this.handleChange} autoComplete="id"/>
                    </FormGroup>
                    <FormGroup>
                        <Label for="email">Email</Label>
                        <Input type="text" name="email" id="email" value={item.email || ''}
                               onChange={this.handleChange} autoComplete="email"/>
                    </FormGroup>
                    <FormGroup>
                        <Label for="contraseña">Contraseña</Label>
                        <Input type="text" name="contraseña" id="contraseña" value={item.contraseña || ''}
                               onChange={this.handleChange} autoComplete="contraseña"/>
                    </FormGroup>
                    <FormGroup>
                        <Label for="positivo">Positivo</Label>
                        <Input type="text" name="positivo" id="positivo" value={item.positivo || ''}
                               onChange={this.handleChange} autoComplete="positivo"/>
                    </FormGroup>
                    <FormGroup>
                        <Label for="contactos">Contactos</Label>
                        <Input type="text" name="contactos" id="contactos" value={item.contactos || ''}
                               onChange={this.handleChange} autoComplete="contactos"/>
                    </FormGroup>

                    <FormGroup>
                        <Button color="primary" type="submit">Guardar</Button>{' '}
                        <Button color="secondary" tag={Link} to="/usuarios">Cancel</Button>
                    </FormGroup>
                </Form>
            </Container>
        </div>
    }
}

export default withRouter(TFGEdit);