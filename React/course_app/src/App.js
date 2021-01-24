import React from 'react'
import MainComponent from './Components/MainComponent'
import { BrowserRouter } from 'react-router-dom'


function App() {
  return (
    <BrowserRouter>
      <div>
        <MainComponent />
      </div>
    </BrowserRouter>
  )
}

export default App