import React from 'react';

const App = (name, content, onClick = (f) => f) => {

    return (
        <h1 onClick={() => onClick(name, content)}>
            {name}님, {content}
        </h1>
    );
};

export default App;