import React, { useState, useEffect } from 'react';
import { motion, AnimatePresence } from 'motion/react';
import { Wifi, Battery, Signal, Clock, ChevronRight } from 'lucide-react';

export const StatusBar = () => {
  const [time, setTime] = useState(new Date());
  const [speed, setSpeed] = useState(Math.floor(Math.random() * 100));

  useEffect(() => {
    const timer = setInterval(() => setTime(new Date()), 1000);
    const speedTimer = setInterval(() => setSpeed(prev => {
      const delta = Math.random() > 0.5 ? 5 : -5;
      return Math.max(0, prev + delta);
    }), 2000);
    return () => {
      clearInterval(timer);
      clearInterval(speedTimer);
    };
  }, []);

  return (
    <div className="h-7 w-full px-4 flex items-center justify-between text-[10px] font-medium z-[100] fixed top-0 pointer-events-none">
      <div className="flex items-center gap-2">
        <span className="font-mono text-blue-400 bg-blue-500/10 px-1 rounded uppercase">
          {speed < 10 ? '0' : ''}{speed} KB/s
        </span>
        <div className="flex items-center gap-1 opacity-60">
          <Signal size={10} />
          <Wifi size={10} />
        </div>
      </div>
      
      <div className="flex items-center gap-2">
        <div className="flex items-center gap-1 opacity-60">
          <Battery size={10} className="rotate-90" />
          <span>80%</span>
        </div>
        <span className="font-bold">
          {time.toLocaleTimeString([], { hour: '2-digit', minute: '2-digit' })}
        </span>
      </div>
    </div>
  );
};
