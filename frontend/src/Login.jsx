import { useState } from "react";
import axios from "axios";

export default function Login({ setLoggedIn }) {
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");

  const handleLogin = async () => {
    try {
      await axios.post("http://localhost:8080/auth/login", {
        username,
        password,
      });
      setLoggedIn(true);
    } catch {
      alert("Invalid credentials");
    }
  };

  return (
    <div style={styles.container}>
      <div style={styles.card}>
        <h2 style={styles.title}>Predictive Maintenance</h2>
        <p style={styles.subtitle}>Login to continue</p>

        <input
          style={styles.input}
          placeholder="Username"
          onChange={(e) => setUsername(e.target.value)}
        />

        <input
          style={styles.input}
          type="password"
          placeholder="Password"
          onChange={(e) => setPassword(e.target.value)}
        />

        <button style={styles.button} onClick={handleLogin}>
          Login
        </button>
      </div>
    </div>
  );
}

const styles = {
  container: {
    position: "fixed",
    top: 0,
    left: 0,
    width: "100%",
    height: "100vh",
    display: "flex",
    justifyContent: "center",
    alignItems: "center",
    background: "linear-gradient(135deg, #0f172a, #020617)",
  },

  card: {
    background: "#1e293b",
    padding: "40px",
    borderRadius: "12px",
    display: "flex",
    flexDirection: "column",
    gap: "15px",
    width: "320px",
    boxShadow: "0 10px 30px rgba(0,0,0,0.5)",
  },

  title: {
    color: "white",
    textAlign: "center",
    marginBottom: "5px",
  },

  subtitle: {
    color: "#94a3b8",
    textAlign: "center",
    marginBottom: "10px",
    fontSize: "14px",
  },

  input: {
    padding: "12px",
    borderRadius: "6px",
    border: "none",
    outline: "none",
    background: "#334155",
    color: "white",
  },

  button: {
    padding: "12px",
    background: "#3b82f6",
    color: "white",
    border: "none",
    borderRadius: "6px",
    cursor: "pointer",
    fontWeight: "bold",
  },
};
