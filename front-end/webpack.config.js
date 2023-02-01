const path = require("path")
const CopyPlugin = require("copy-webpack-plugin")

module.exports = {
    mode: "production",
    entry: [
        "./public/login/login.ts",
        "./public/cadastro-empresa/cadastro-empresa.ts",
        "./public/cadastro-candidato/cadastro-candidato.ts",
        "./public/classes/Usuario.ts",
        "./public/classes/CandidatoUsuario.ts",
        "./public/classes/EmpresaUsuario.ts",
        "./public/perfil-candidato/perfil-candidato.ts",
        "./public/perfil-empresa/perfil-empresa.ts",    
    ],
    devServer: {
        static: path.join(__dirname, "dist"),
        port: 9000,
        hot: true
    },
    output: {
        filename: "app.min.js",
        path: path.join(__dirname, "dist")
    },
    plugins: [
        new CopyPlugin({
            patterns: [{from: "./public"}]
        })
    ],
    resolve: {
        extensions: [ '.ts', '.js' ]
    },
    module: {
        rules: [{
            test: /\.ts$/,
            use: "ts-loader",
            exclude: /node_modules/
        }]
    }
}