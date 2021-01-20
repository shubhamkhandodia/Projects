const path = require("path");
const webpack = require('webpack'); //to access built-in plugins
const HtmlWebpackPlugin = require('html-webpack-plugin'); //installed via npm
const { CleanWebpackPlugin } = require('clean-webpack-plugin');


module.exports = {
  entry: {

    app: './src/index.js',
  },
  
  devServer: {
    port: 8080,
    contentBase: ['./src', './build'], // both src and output dirs
    inline: true,
    hot: true
},
  
  module: {
    rules: [
      {
        test: /.(js|jsx)$/,
        exclude: /node_modules/,
        use: {
          loader: "babel-loader"
        }
      },
      {
        test: /\.css$/i,
        use: ["style-loader", "css-loader"],
      },
      
    ]
  },
  plugins: [

  new CleanWebpackPlugin(),
  new webpack.HotModuleReplacementPlugin(),
    new HtmlWebpackPlugin({
      filename: "index.html",
      template: path.join(__dirname, "src", "index.html")
    })
  ],



  output: {
    path: path.join(__dirname, "build"),
    filename: "bundle.js"
  },
};