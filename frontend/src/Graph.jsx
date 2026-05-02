import { LineChart, Line, XAxis, YAxis, Tooltip } from "recharts";

export default function Graph({ history }) {
  return (
    <LineChart width={500} height={250} data={history}>
      <XAxis dataKey="index" />
      <YAxis domain={[0, 1]} />
      <Tooltip />
      <Line dataKey="prediction" />
    </LineChart>
  );
}